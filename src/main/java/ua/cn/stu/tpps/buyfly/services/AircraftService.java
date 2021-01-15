package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Flight;

import java.util.Collection;

public interface AircraftService extends GenericService<Aircraft> {

    Collection<Flight> getFlightsForAircraft(Integer aircraft);
}
