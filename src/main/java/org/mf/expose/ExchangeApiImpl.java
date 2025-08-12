package org.mf.expose;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.mf.service.EnquiryService;
import org.openapi.quarkus.exchange_yaml.api.ExchangeApi;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

@ApplicationScoped
@AllArgsConstructor
public class ExchangeApiImpl implements ExchangeApi {

    @Inject
    EnquiryService exchangeService;

    @Override
    public ExchangeRate getExchangeByDni(String dni) {
        return exchangeService.getExchangeWithDni(dni);
    }
}
