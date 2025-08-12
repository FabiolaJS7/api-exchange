package org.mf.connector;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.mf.bean.ExchangeRateExt;

@RegisterRestClient(configKey = "api-proxy")
@Path("/today.json")
public interface ExchangeApiClient {

    //Método que devuelve el tipo de cambio del servicio externo.
    @GET
    ExchangeRateExt getTodayExchangeRate();

}
