package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.Flight;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.Flight}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface FlightDao extends GenericDao<Flight> {

    /**
     * Get collection of flights by direction between two cities.
     *
     * @param originCityId      departure city unique identifier
     * @param destinationCityId destination city unique identifier
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getByDirection(Integer originCityId, Integer destinationCityId) throws PersistenceException;


    /**
     * Get collection of flights between dates.
     *
     * @param startDate search starts with this date
     * @param endDate   search ends with this date
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws PersistenceException;


    /**
     * Get collection of flights for specified aircraft.
     *
     * @param aircraftId aircraft unique identifier
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getByAircraft(Integer aircraftId) throws PersistenceException;


    /**
     * Get collection of flights between dates for specified aircraft.
     *
     * @param startDate  search starts with this date
     * @param endDate    search ends with this date
     * @param aircraftId aircraft unique identifier
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getByDatesAndAircraft(LocalDateTime startDate, LocalDateTime endDate, Integer aircraftId)
        throws PersistenceException;


    /**
     * Get collection of flights between countries.
     *
     * @param origin      origin country name
     * @param destination destination country name
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getByCountries(String origin, String destination) throws PersistenceException;


    /**
     * Get collection of flights between airports.
     *
     * @param originAirportId      origin airport unique identifier
     * @param destinationAirportId destination airport unique identifier
     * @return collection of flights
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getByAirports(Integer originAirportId, Integer destinationAirportId) throws PersistenceException;
}
