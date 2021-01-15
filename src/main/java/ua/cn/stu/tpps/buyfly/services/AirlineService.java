package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.Airline;

public interface AirlineService extends GenericService<Airline> {

    Airline getByAircraft(Integer aircraftId);
}
