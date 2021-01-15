package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.BoardingPassDao;
import ua.cn.stu.tpps.buyfly.domain.BoardingPass;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Implements BoardingPass DAO.
 */
public class BoardingPassDaoJpa extends GenericDaoJpa<BoardingPass> implements BoardingPassDao {

    public BoardingPassDaoJpa() {
        super(BoardingPass.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#setStatus(String, Integer)
     */
    @Override
    public void setStatus(String status, Integer boardingPassId) throws PersistenceException {
        executeQuery("setStatus", true, true, status, boardingPassId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getByStatus(java.lang.String)
     */
    @Override
    public Collection<BoardingPass> getByStatus(String status) throws PersistenceException {
        return executeQuery("getByStatus", true, false, status);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getByFlight(java.lang.Integer)
     */
    @Override
    public Collection<BoardingPass> getByFlight(Integer flightId) throws PersistenceException {
        return executeQuery("getByFlight", true, false, flightId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getByStatusAndFlight(java.lang.Integer, java.lang.String)
     */
    @Override
    public Collection<BoardingPass> getByStatusAndFlight(Integer flightId, String status) throws PersistenceException {
        return executeQuery("getByStatusAndFlight", true, false, flightId, status);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getByCustomer(java.lang.Integer)
     */
    @Override
    public Collection<BoardingPass> getByCustomer(Integer customerId) throws PersistenceException {
        return executeQuery("getByCustomer", true, false, customerId);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getCurrentByCustomer(java.lang.Integer, java.time.LocalDateTime)
     */
    @Override
    public Collection<BoardingPass> getCurrentByCustomer(Integer customerId, LocalDateTime date) throws PersistenceException {
        return executeQuery("getCurrentByCustomer", true, false, customerId, date);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.BoardingPassDao#getCompletedByCustomer(java.lang.Integer, java.time.LocalDateTime)
     */
    @Override
    public Collection<BoardingPass> getCompletedByCustomer(Integer customerId, LocalDateTime date) throws PersistenceException {
        return executeQuery("getCompletedByCustomer", true, false, customerId, date);
    }
}
