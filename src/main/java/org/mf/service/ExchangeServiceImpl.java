package org.mf.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.mf.bean.ExchangeRateExt;
import org.mf.mapper.ExchangeMapper;
import org.mf.repository.ExchangeRepository;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

import java.util.Date;

@ApplicationScoped
public class ExchangeServiceImpl implements ExchangeService{
    @Inject
    Logger logger;

    @Inject
    ExchangeServiceProxy exchangeServiceProxy;
    @Inject
    ExchangeRepository exchangeRepository;

    @Override
    @Transactional
    public ExchangeRate getExchangeWithDni(String dni) {
        ExchangeRateExt exchangeRateExt = exchangeServiceProxy.getExchangeRate();
        int countEnquiryByDni = exchangeRepository.findByDni(dni);

        logger.info("exchangeRateExt.getSunat() " + exchangeRateExt.getSunat());
        logger.info("countEnquiryByDni " + countEnquiryByDni);

        if (countEnquiryByDni < 10) {
            logger.info("Saving enquiry for DNI: " + dni);
            exchangeRepository.saveEnquiry(dni, Double.valueOf(exchangeRateExt.getSunat()));
        } else {
            logger.info("Enquiry limit reached for DNI: " + dni);
        }

        return ExchangeMapper.INSTANCE.getExchangeRateByExt(exchangeRateExt);
    }
}
