package org.mf.expose;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.mf.mapper.TransactionMapper;
import org.mf.repository.TransactionRepository;
import org.mf.service.AzureAIService;
import org.openapi.quarkus.exchange_yaml.api.TransactionsApi;
import org.openapi.quarkus.exchange_yaml.model.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class SearchTransactionIaImpl implements TransactionsApi {

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    AzureAIService azureAIService;

    @Override
    public List<Transaction> apiTransactionsGet(String word) {
         List<Transaction> transactions = new ArrayList<>();
        /** Transaction transaction = new Transaction();
        transaction.setCompanyName("Ripley mock");
        transactions.add(transaction); **/

        // Usar Azure Open AI para inferencias semánticas
        String strInferredCompany = azureAIService.getSemanticMatch(word);
        System.out.println("inferredCompany: " + strInferredCompany);

        List<String> companyNames = parseCompaniesJson(strInferredCompany);

         transactions = transactionRepository.searchByKeywords(companyNames)
                .stream()
                .map(TransactionMapper.INSTANCE::toTransactionBean)
                .toList();


        return transactions;
    }

    public List<String> parseCompaniesJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Si el JSON es un array simple de strings
            return mapper.readValue(json, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
