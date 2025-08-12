package org.mf.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mf.entity.Enquiry;

import java.util.List;

@ApplicationScoped
public class ExchangeRepository implements PanacheRepository<Enquiry> {

    //Busca todos los registros que existen en la bd para de las consultas que ha realizado el dni
    public int findByDni(String dni){
        return find("dni", dni).list().size();
    }

    //Guarda la consulta realizada
    public void saveEnquiry(String dni, Double exchangeRate) {
        Enquiry enquiry = new Enquiry();
        enquiry.setDni(dni);
        enquiry.setExchangeRate(exchangeRate);
        enquiry.setConsultDate(new java.util.Date());
        persist(enquiry);
    }

}
