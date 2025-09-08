package org.mf.service;

import org.openapi.quarkus.exchange_yaml.model.Transaction;

import java.util.List;

public interface TransactionService {

    Long createTransaction(Transaction transactionBean);
    List<Transaction> listAll();
}
