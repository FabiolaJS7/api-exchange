package org.mf.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;
import org.mf.bean.ExchangeRateExt;
import org.mf.mapper.ExchangeMapper;
import org.mf.repository.EnquiryRepository;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

import java.util.Date;

@ApplicationScoped
public class EnquiryServiceImpl implements EnquiryService {
    @Inject
    Logger logger;

    @Inject
    ExchangeServiceProxy exchangeServiceProxy;
    @Inject
    EnquiryRepository enquiryRepository;

    @Override
    @Transactional
    public ExchangeRate getExchangeWithDni(String dni) {
        ExchangeRate exchangeRate = new ExchangeRate();
        logger.info("DNI Person consulting: " + dni);

        try {
            ExchangeRateExt exchangeRateExt = exchangeServiceProxy.getExchangeRate();

            if (validateDni(dni, exchangeRate)) {
                int countEnquiryByDni = enquiryRepository.findByDniAndDate(dni, new Date());

                logger.info("sunat exchangeRateExt " + exchangeRateExt.getSunat());
                logger.info("countEnquiryByDni " + countEnquiryByDni);

                if (countEnquiryByDni < 10) {
                    logger.info("Saving enquiry for DNI: " + dni);
                    enquiryRepository.saveEnquiry(dni, Double.valueOf(exchangeRateExt.getSunat()));
                    exchangeRate = ExchangeMapper.INSTANCE.getExchangeRateByExt(exchangeRateExt);
                } else {
                    logger.info("Our limit for consultations by DNI per day: " + dni);
                    exchangeRate.setMessage("Our limit for consultations by DNI per day: " + dni);
                }

            }

        } catch (Exception e) {
            logger.error("Error fetching exchange rate: " + e.getMessage(), e);
            exchangeRate.setMessage("Error fetching exchange rate: " + e.getMessage());
            exchangeRate.setStatus(false);
            return exchangeRate;
        }

        return exchangeRate;

    }

    private boolean validateDni(String dni, ExchangeRate exchangeRate) {
        String message = "DNI validated. ";
        boolean result = true;

        if (dni == null || dni.isEmpty()) {
            logger.error("DNI is null or empty");
            message = "DNI is null or empty";
            result = false;
        }
        assert dni != null;
        if (dni.length() != 8) {
            logger.error("DNI must be 8 characters long");
            message = "DNI must be 8 characters long";
            result = false;
        }
        if (!dni.matches("\\d{8}")) {
            logger.error("DNI must contain only numbers");
            message = "DNI must contain only numbers";
            result = false;
        }

        exchangeRate.setMessage(message);
        exchangeRate.setStatus(result);

        return result;
    }
}
