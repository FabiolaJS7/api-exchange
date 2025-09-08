package org.mf.expose;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.mf.service.TransactionService;
import org.openapi.quarkus.exchange_yaml.api.TransactionApi;
import org.openapi.quarkus.exchange_yaml.model.Transaction;

import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class TransactionApiImpl implements TransactionApi {

    @Inject
    TransactionService transactionService;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transaction.setId(String.valueOf(transactionService.createTransaction(transaction)));
        return transaction;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionService.listAll();
    }


}
