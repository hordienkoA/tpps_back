package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.AirlineDao;
import ua.cn.stu.tpps.buyfly.domain.Airline;

import javax.persistence.PersistenceException;

/**
 * Implements Airline DAO.
 */
public class AirlineDaoJpa extends GenericDaoJpa<Airline> implements AirlineDao {

    public AirlineDaoJpa() {
        super(Airline.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see AirlineDao#getByAircraft(Integer)
     */
    @Override
    public Airline getByAircraft(Integer aircraftId) throws PersistenceException {
        return executeQuery("getAirlineByAircraft", true, true, aircraftId);
    }
}
