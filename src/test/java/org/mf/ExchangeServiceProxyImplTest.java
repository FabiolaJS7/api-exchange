package org.mf;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mf.bean.ExchangeRateExt;
import org.mf.service.ExchangeServiceProxyImpl;

@QuarkusTest
public class ExchangeServiceProxyImplTest {

    @Inject
    ExchangeServiceProxyImpl exchangeServiceProxyImpl;

    @Test
    void shouldConsultExternalService_getExchangeRate() {
        ExchangeRateExt exchangeRateExtra = exchangeServiceProxyImpl.getExchangeRate();
        System.out.println(exchangeRateExtra);
    }
}
