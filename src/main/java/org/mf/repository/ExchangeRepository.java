package org.mf.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.mf.entity.Enquiry;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ExchangeRepository implements PanacheRepository<Enquiry> {

    //Busca todos los registros que existen en la bd para de las consultas que ha realizado el dni
    public int findByDniAndDate(String dni, Date consultDate){
        // Obtener el inicio del día de la fecha proporcionada
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(consultDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();

        // Obtener el final del día de la fecha proporcionada
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();

        // Buscar registros que coincidan con el dni y que estén dentro del rango de fechas
        return find("dni = ?1 and consultDate >= ?2 and consultDate < ?3", dni, startDate, endDate).list().size();
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
