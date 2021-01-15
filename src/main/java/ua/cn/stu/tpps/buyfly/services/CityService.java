package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.City;

import java.util.Collection;

public interface CityService extends GenericService<City> {
    City getCity(String cityName);

    Collection<City> getCitiesByCountry(String countryName);

    Collection<City> getCitiesByAirline(String airlineName);
}
