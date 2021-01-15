package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.FlightDao;
import ua.cn.stu.tpps.buyfly.domain.Flight;
import ua.cn.stu.tpps.buyfly.services.FlightService;

import java.time.LocalDateTime;
import java.util.Collection;

@Transactional
public class FlightServiceImpl extends GenericServiceImpl<Flight, FlightDao> implements FlightService {

    @Autowired
    protected void setDao(FlightDao flightDao) {
        dao = flightDao;
    }

    @Override
    public Collection<Flight> getByDirection(Integer originCityId, Integer destinationCityId) {
        return dao.getByDirection(originCityId, destinationCityId);
    }

    @Override
    public Collection<Flight> getBetweenDates(LocalDateTime departure, LocalDateTime arrival) {
        return dao.getBetweenDates(departure, arrival);
    }

    @Override
    public Collection<Flight> getByAircraft(Integer aircraftId) {
        return dao.getByAircraft(aircraftId);
    }

    @Override
    public Collection<Flight> getByDatesAndAircraft(LocalDateTime departure, LocalDateTime arrival, Integer aircraftId) {
        return dao.getByDatesAndAircraft(departure, arrival, aircraftId);
    }

    @Override
    public Collection<Flight> getByCountries(String origin, String destination) {
        return dao.getByCountries(origin, destination);
    }

    @Override
    public Collection<Flight> getByAirports(Integer originAirportId, Integer destinationAirportId) {
        return dao.getByAirports(originAirportId, destinationAirportId);
    }

    @Override
    public Collection<Flight> getCurrentFlightsByCustomer(Integer customerId) {
        //TODO Implement it
        return null;
    }

    @Override
    public Collection<Flight> getPreviousFlightsByCustomer(Integer customerId) {
        //TODO Implement it
        return null;
    }
}
