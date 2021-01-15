package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.BoardingPass;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.BoardingPass}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface BoardingPassDao extends GenericDao<BoardingPass> {

    /**
     * Set status of boarding pass specified by unique identifier.
     *
     * @param boardingPassId boarding pass unique identifier
     * @param status         status to be set
     * @throws PersistenceException if error occurs
     */
    void setStatus(String status, Integer boardingPassId) throws PersistenceException;


    /**
     * Get collection of boarding passes with desired status.
     *
     * @param status status to find
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getByStatus(String status) throws PersistenceException;


    /**
     * Get collection of boarding passes for a flight.
     *
     * @param flightId flight unique identifier
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getByFlight(Integer flightId) throws PersistenceException;


    /**
     * Get collection of boarding passes for a flight with desired status.
     *
     * @param flightId flight unique identifier
     * @param status   status to find
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getByStatusAndFlight(Integer flightId, String status) throws PersistenceException;


    /**
     * Get collection of all customer's boarding passes.
     *
     * @param customerId customer unique identifier
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getByCustomer(Integer customerId) throws PersistenceException;


    /**
     * Get collection of customer's boarding passes which are not completed yet (are after specified (current) date).
     *
     * @param customerId customer unique identifier
     * @param date       date after which flights will be searched
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getCurrentByCustomer(Integer customerId, LocalDateTime date) throws PersistenceException;


    /**
     * Get collection of customer's completed boarding passes (before specified (current) date).
     *
     * @param customerId customer unique identifier
     * @param date       date after which flights will be searched
     * @return collection of boarding passes
     * @throws PersistenceException if error occurs
     */
    Collection<BoardingPass> getCompletedByCustomer(Integer customerId, LocalDateTime date) throws PersistenceException;
}
