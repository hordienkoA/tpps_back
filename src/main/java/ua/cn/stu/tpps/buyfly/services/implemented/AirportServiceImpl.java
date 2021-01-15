package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.AirportDao;
import ua.cn.stu.tpps.buyfly.domain.Airport;
import ua.cn.stu.tpps.buyfly.services.AirportService;

import java.util.Collection;

/**
 * Implementation of AirportService
 */
@Transactional
public class AirportServiceImpl extends GenericServiceImpl<Airport, AirportDao> implements AirportService {

    @Autowired
    protected void setDao(AirportDao airportDao) {
        dao = airportDao;
    }

    @Override
    public Collection<Airport> getByCountry(String countryName) {
        return dao.getByCountry(countryName);
    }

    @Override
    public Collection<Airport> getByCity(String cityName) {
        return dao.getByCity(cityName);
    }
}
