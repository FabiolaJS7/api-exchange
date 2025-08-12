package org.mf.service;

import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

public interface ExchangeService {

    ExchangeRate getExchangeWithDni(String dni);
}
