package org.mf.bean;


public class ExchangeRateExt {

    private String fecha;
    private String sunat;
    private String compra;
    private String venta;

    public ExchangeRateExt() {
    }

    public ExchangeRateExt(String fecha, String sunat, String compra, String venta) {
        this.fecha = fecha;
        this.sunat = sunat;
        this.compra = compra;
        this.venta = venta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSunat() {
        return sunat;
    }

    public void setSunat(String sunat) {
        this.sunat = sunat;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }
}
