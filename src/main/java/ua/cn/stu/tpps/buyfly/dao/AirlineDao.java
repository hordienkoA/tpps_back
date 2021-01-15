package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.Airline;

import javax.persistence.PersistenceException;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.Airline}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface AirlineDao extends GenericDao<Airline> {

    /**
     * Get airline for aircraft, specified by unique id.
     *
     * @param aircraftId aircraft unique identifier
     * @return airline entity
     * @throws PersistenceException if error occurs
     */
    Airline getByAircraft(Integer aircraftId) throws PersistenceException;
}
