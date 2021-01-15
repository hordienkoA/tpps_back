package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.Flight;

import java.time.LocalDateTime;
import java.util.Collection;

public interface FlightService extends GenericService<Flight> {
    Collection<Flight> getByDirection(Integer originCityId, Integer destinationCityId);

    Collection<Flight> getBetweenDates(LocalDateTime departure, LocalDateTime arrival);

    Collection<Flight> getByAircraft(Integer aircraftId);

    Collection<Flight> getByDatesAndAircraft(LocalDateTime departure, LocalDateTime arrival, Integer aircraftId);

    Collection<Flight> getByCountries(String origin, String destination);

    Collection<Flight> getByAirports(Integer originAirportId, Integer destinationAirportId);

    Collection<Flight> getCurrentFlightsByCustomer(Integer customerId);

    Collection<Flight> getPreviousFlightsByCustomer(Integer customerId);
}
