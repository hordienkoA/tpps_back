package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.AirportDao;
import ua.cn.stu.tpps.buyfly.domain.Airport;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Implements Airport DAO.
 */
public class AirportDaoJpa extends GenericDaoJpa<Airport> implements AirportDao {

    public AirportDaoJpa() {
        super(Airport.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see AirportDao#getByCountry(java.lang.String)
     */
    @Override
    public Collection<Airport> getByCountry(String countryName) throws PersistenceException {
        return executeQuery("getByCountry", true, false, countryName);
    }


    /**
     * (non-Javadoc)
     *
     * @see AirportDao#getByCity(java.lang.String)
     */
    @Override
    public Collection<Airport> getByCity(String cityName) throws PersistenceException {
        return executeQuery("getByCity", true, false, cityName);
    }
}
