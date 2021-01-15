package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.City;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.City}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface CityDao extends GenericDao<City> {

    /**
     * Get city by name.
     *
     * @param cityName city name
     * @return City entity
     * @throws PersistenceException if error occurs
     */
    City getCity(String cityName) throws PersistenceException;


    /**
     * Get collection of cities for country, specified by name.
     *
     * @param countryName country name
     * @return collection of cities
     * @throws PersistenceException if error occurs
     */
    Collection<City> getCitiesByCountry(String countryName) throws PersistenceException;


    /**
     * Get collection of cities for airline, specified by name.
     *
     * @param airlineName airline name
     * @return collection of cities
     * @throws PersistenceException if error occurs
     */
    Collection<City> getCitiesByAirline(String airlineName) throws PersistenceException;
}
