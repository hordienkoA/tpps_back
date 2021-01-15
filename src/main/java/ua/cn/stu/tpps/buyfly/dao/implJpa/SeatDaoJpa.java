package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.SeatDao;
import ua.cn.stu.tpps.buyfly.domain.Seat;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Implements Seat DAO.
 */
public class SeatDaoJpa extends GenericDaoJpa<Seat> implements SeatDao {

    public SeatDaoJpa() {
        super(Seat.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.SeatDao#getByClass(Integer, String)
     */
    @Override
    public Collection<Seat> getByClass(Integer aircraftId, String seatClass) throws PersistenceException {
        return executeQuery("getByClass", true, false, aircraftId, seatClass);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.SeatDao#getFreeSeats(Integer)
     */
    @Override
    public Collection<Seat> getFreeSeats(Integer aircraftId) throws PersistenceException {
        return executeQuery("getFreeSeats", true, false, aircraftId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.SeatDao#getFreeByPriceRange(Integer, double, double)
     */
    @Override
    public Collection<Seat> getFreeByPriceRange(Integer aircraftId, double lowestPrice, double highestPrice) throws PersistenceException {
        return executeQuery("getFreeByPriceRange", true, false, aircraftId, lowestPrice, highestPrice);
    }
}
