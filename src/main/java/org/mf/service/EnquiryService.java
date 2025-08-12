package org.mf.service;

import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

public interface EnquiryService {

    ExchangeRate getExchangeWithDni(String dni);
}
