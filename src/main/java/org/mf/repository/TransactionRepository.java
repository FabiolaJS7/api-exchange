package org.mf.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mf.entity.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {

    public Long saveTransaction(Transaction transaction) {
        persist(transaction);
        return transaction.getId();
    }

    // Método para obtener todas las transacciones
    public List<Transaction> listAllTransactions() {
        return listAll();
    }

    // Buscar por CompanyName
    public List<Transaction> findByCompanyName(String companyName) {
        return list("companyName", companyName);
    }

    // Buscar por descripción
    public List<Transaction> searchByDescription(String keyword) {
        String query = "companyName like ?1 or description like ?1";
        String param = "%" + keyword + "%";
        return list(query, param);
    }

    // Buscar por CompanyName que contenga...
    public List<Transaction> findByCompanyNameContain(List<String> partialNames) {
        if (partialNames == null || partialNames.isEmpty()) {
            return List.of();
        }

        //Construyendo Query dinámica con OR  para cada nombre parcial
        StringBuilder queryBuilder = new StringBuilder();
        Object[] params = new Object[partialNames.size()];
        for (int i = 0; i < partialNames.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" or ");
            }

            queryBuilder.append("companyName like ?").append(i + 1);
            params[i] = "%" + partialNames.get(i) + "%";
        }

        return list(queryBuilder.toString(), params);
    }

    public List<Transaction> searchByKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return Collections.emptyList();
        }

        // Build query dynamically with OR conditions for each keyword on both columns
        StringBuilder queryBuilder = new StringBuilder();
        List<String> params = new ArrayList<>();

        for (int i = 0; i < keywords.size(); i++) {
            if (i > 0) {
                queryBuilder.append(" or ");
            }
            queryBuilder.append("(companyName like ?").append(i * 2 + 1)
                    .append(" or description like ?").append(i * 2 + 2).append(")");
            // Add parameters with wildcards
            params.add("%" + keywords.get(i) + "%"); // for companyName
            params.add("%" + keywords.get(i) + "%"); // for description
        }

        // Convert params list to array
        String[] paramsArray = params.toArray(new String[0]);

        // Execute query with all parameters
        return list(queryBuilder.toString(), paramsArray);
    }

}
