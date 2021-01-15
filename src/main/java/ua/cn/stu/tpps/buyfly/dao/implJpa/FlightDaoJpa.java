package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.FlightDao;
import ua.cn.stu.tpps.buyfly.domain.Flight;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Implements Flight DAO.
 */
public class FlightDaoJpa extends GenericDaoJpa<Flight> implements FlightDao {

    public FlightDaoJpa() {
        super(Flight.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getByDirection(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Collection<Flight> getByDirection(Integer originCityId, Integer destinationCityId)
        throws PersistenceException {
        return executeQuery("getByDirection", true, false, originCityId, destinationCityId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getBetweenDates(LocalDateTime, LocalDateTime)
     */
    @Override
    public Collection<Flight> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate) throws PersistenceException {
        return executeQuery("getBetweenDates", true, false, startDate, endDate);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getByAircraft(java.lang.Integer)
     */
    @Override
    public Collection<Flight> getByAircraft(Integer aircraftId) throws PersistenceException {
        return executeQuery("getByAircraft", true, false, aircraftId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getByDatesAndAircraft(LocalDateTime, LocalDateTime, Integer)
     */
    @Override
    public Collection<Flight> getByDatesAndAircraft(LocalDateTime startDate, LocalDateTime endDate, Integer aircraftId)
        throws PersistenceException {
        return executeQuery("getByDatesAndAircraft", true, false, startDate, endDate, aircraftId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getByCountries(java.lang.String, java.lang.String)
     */
    @Override
    public Collection<Flight> getByCountries(String origin, String destination) throws PersistenceException {
        return executeQuery("getByCountries", true, false, origin, destination);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.FlightDao#getByAirports(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public Collection<Flight> getByAirports(Integer originAirportId, Integer destinationAirportId)
        throws PersistenceException {
        return executeQuery("getByAirports", true, false, originAirportId, destinationAirportId);
    }
}
