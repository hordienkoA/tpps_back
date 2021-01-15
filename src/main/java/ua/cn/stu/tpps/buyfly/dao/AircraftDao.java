package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Flight;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.Aircraft}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface AircraftDao extends GenericDao<Aircraft> {

    /**
     * Get collection of flights for aircraft.
     *
     * @param aircraftId unique identifier of aircraft
     * @return collection of flights for aircraft
     * @throws PersistenceException if error occurs
     */
    Collection<Flight> getFlightsForAircraft(Integer aircraftId) throws PersistenceException;
}
