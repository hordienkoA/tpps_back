package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.Airport;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.Airport}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface AirportDao extends GenericDao<Airport> {

    /**
     * Get collection of airports for country, specified by name.
     *
     * @param countryName name of country to search airports
     * @return collection of airports
     * @throws PersistenceException if error occurs
     */
    Collection<Airport> getByCountry(String countryName) throws PersistenceException;


    /**
     * Get collection of airports for city, specified by name.
     *
     * @param cityName name of city to search airports
     * @return collection of airports
     * @throws PersistenceException if error occurs
     */
    Collection<Airport> getByCity(String cityName) throws PersistenceException;
}
