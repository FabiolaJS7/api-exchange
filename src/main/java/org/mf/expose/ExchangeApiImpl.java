package org.mf.expose;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import org.jboss.logging.Logger;
import org.mf.service.ExchangeService;
import org.openapi.quarkus.exchange_yaml.api.ExchangeApi;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;
import org.openapi.quarkus.exchange_yaml.model.Person;

@ApplicationScoped
@AllArgsConstructor
public class ExchangeApiImpl implements ExchangeApi {

    @Inject
    Logger logger;

    @Inject
    ExchangeService exchangeService;

    @Override
    public ExchangeRate getExchangeWithDni(Person person) {
        logger.info("DNI Person consulting: {}" + person);
        return exchangeService.getExchangeWithDni(person.getDni());
    }
}
