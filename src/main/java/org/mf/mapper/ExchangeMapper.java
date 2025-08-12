package org.mf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.mf.bean.ExchangeRateExt;
import org.openapi.quarkus.exchange_yaml.model.ExchangeRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface ExchangeMapper {

    ExchangeMapper INSTANCE = Mappers.getMapper(ExchangeMapper.class);

    @Mapping(source = "fecha", target = "dateToday", qualifiedByName = "stringToLocalDate")
    @Mapping(source = "sunat", target = "exchangeRateSunat")
    @Mapping(source = "compra", target = "buy")
    @Mapping(source = "venta", target = "sell")
    ExchangeRate getExchangeRateByExt(ExchangeRateExt exchangeRateExt);

    //Convierte un String en LocalDate
    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
