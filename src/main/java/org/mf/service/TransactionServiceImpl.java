package org.mf.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.mf.mapper.TransactionMapper;
import org.mf.repository.TransactionRepository;
import org.openapi.quarkus.exchange_yaml.model.Transaction;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransactionServiceImpl implements TransactionService {

    @Inject
    TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Long createTransaction(Transaction  transactionBean) {
        return transactionRepository.saveTransaction(TransactionMapper.INSTANCE.toTransaction(transactionBean));

    }

    @Override
    public List<Transaction> listAll() {
        return transactionRepository.listAllTransactions()
                .stream()
                .map(TransactionMapper.INSTANCE::toTransactionBean)
                .collect(Collectors.toList());
    }
}
