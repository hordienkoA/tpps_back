package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.AircraftDao;
import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Flight;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Implements Aircraft DAO.
 */
public class AircraftDaoJpa extends GenericDaoJpa<Aircraft> implements AircraftDao {

    public AircraftDaoJpa() {
        super(Aircraft.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see AircraftDao#getFlightsForAircraft(Integer)
     */
    @Override
    public Collection<Flight> getFlightsForAircraft(Integer aircraftId) throws PersistenceException {
        return executeQuery("getFlightsForAircraft", true, false, aircraftId);
    }
}
