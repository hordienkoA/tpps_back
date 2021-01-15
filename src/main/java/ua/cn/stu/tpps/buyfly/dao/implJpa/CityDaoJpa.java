package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.CityDao;
import ua.cn.stu.tpps.buyfly.domain.City;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Implements City DAO.
 */
public class CityDaoJpa extends GenericDaoJpa<City> implements CityDao {

    public CityDaoJpa() {
        super(City.class);
    }

    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.CityDao#getCity(java.lang.String)
     */
    @Override
    public City getCity(String cityName) throws PersistenceException {
        return executeQuery("getCityByName", true, true, cityName);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.CityDao#getCitiesByCountry(java.lang.String)
     */
    @Override
    public Collection<City> getCitiesByCountry(String countryName) throws PersistenceException {
        return executeQuery("getCitiesByCountry", true, false, countryName);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.CityDao#getCitiesByAirline(java.lang.String)
     */
    @Override
    public Collection<City> getCitiesByAirline(String airlineName) throws PersistenceException {
        return executeQuery("getCitiesByAirline", true, false, airlineName);
    }
}
