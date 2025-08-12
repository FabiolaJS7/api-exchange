package org.mf.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateExt {

    public String fecha;
    public String sunat;
    public String compra;
    public String venta;
}
