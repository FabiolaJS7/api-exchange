package org.mf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.mf.bean.ExchangeRateExt;
import org.mf.connector.ExchangeApiClient;

@ApplicationScoped
public class ExchangeServiceImplProxy implements ExchangeServiceProxy {

    @Inject
    Logger logger;

    private final ExchangeApiClient exchangeApiClient;

    public ExchangeServiceImplProxy(@RestClient ExchangeApiClient exchangeApiClient) {
        this.exchangeApiClient = exchangeApiClient;
    }

    @Override
    public ExchangeRateExt getExchangeRate() {
        try {
            return exchangeApiClient.getTodayExchangeRate();
        } catch (Exception e) {
            logger.error("Error fetching exchange rate: " + e.getMessage(), e);
            return new ExchangeRateExt(); // Return an empty object or handle it as needed
        }

    }

}
