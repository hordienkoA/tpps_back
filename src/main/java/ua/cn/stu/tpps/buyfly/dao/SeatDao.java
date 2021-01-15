package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.Seat;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.Seat}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface SeatDao extends GenericDao<Seat> {

    /**
     * Get collection of seats by class (fare basis).
     *
     * @param aircraftId aircraft unique identifier to get sets for
     * @param seatClass  seat class (fare basis)
     * @return collection of seats
     * @throws PersistenceException if error occurs
     */
    Collection<Seat> getByClass(Integer aircraftId, String seatClass) throws PersistenceException;


    /**
     * Get collection of free seats.
     *
     * @param aircraftId aircraft unique identifier to get sets for
     * @return collection of free seats
     * @throws PersistenceException if error occurs
     */
    Collection<Seat> getFreeSeats(Integer aircraftId) throws PersistenceException;


    /**
     * Get collection of free seats by price range.
     *
     * @param aircraftId   aircraft unique identifier to get sets for
     * @param lowestPrice  lower limit of price
     * @param highestPrice upper limit of price
     * @return collection of seats
     * @throws PersistenceException if error occurs
     */
    Collection<Seat> getFreeByPriceRange(Integer aircraftId, double lowestPrice, double highestPrice)
        throws PersistenceException;
}
