package org.mf.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.mf.bean.ExchangeRateExt;
import org.mf.mapper.ExchangeMapper;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

@ApplicationScoped
public class ExchangeServiceImpl implements ExchangeService{
    @Inject
    Logger logger;

    @Inject
    ExchangeServiceProxy exchangeServiceProxy;
    @Override
    public ExchangeRate getExchangeWithDni(String dni) {
        ExchangeRateExt exchangeRateExt = exchangeServiceProxy.getExchangeRate();
        return ExchangeMapper.INSTANCE.getExchangeRateByExt(exchangeRateExt);
    }
}
