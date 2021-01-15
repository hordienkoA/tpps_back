package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.CityDao;
import ua.cn.stu.tpps.buyfly.domain.City;
import ua.cn.stu.tpps.buyfly.services.CityService;

import java.util.Collection;

/**
 * Implementation of CityService
 */
@Transactional
public class CityServiceImpl extends GenericServiceImpl<City, CityDao> implements CityService {

    @Autowired
    protected void setDao(CityDao cityDao) {
        dao = cityDao;
    }

    @Override
    public City getCity(String cityName) {
        return dao.getCity(cityName);
    }

    @Override
    public Collection<City> getCitiesByCountry(String countryName) {
        return dao.getCitiesByCountry(countryName);
    }

    @Override
    public Collection<City> getCitiesByAirline(String airlineName) {
        return dao.getCitiesByAirline(airlineName);
    }
}
