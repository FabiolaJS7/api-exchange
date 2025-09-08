package org.mf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mf.bean.TransactionBean;
import org.mf.entity.Transaction;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(org.openapi.quarkus.exchange_yaml.model.Transaction transactionBean);
    org.openapi.quarkus.exchange_yaml.model.Transaction toTransactionBean(Transaction transaction);
}
