package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.Airport;

import java.util.Collection;

public interface AirportService extends GenericService<Airport> {
    Collection<Airport> getByCountry(String countryName);

    Collection<Airport> getByCity(String cityName);
}
