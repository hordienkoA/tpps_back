package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.AircraftDao;
import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Flight;
import ua.cn.stu.tpps.buyfly.services.AircraftService;

import java.util.Collection;

/**
 * Implementation of AircraftService
 */
@Transactional
public class AircraftServiceImpl extends GenericServiceImpl<Aircraft, AircraftDao> implements AircraftService {

    @Autowired
    protected void setDao(AircraftDao aircraftDao) {
        dao = aircraftDao;
    }

    @Override
    public Collection<Flight> getFlightsForAircraft(Integer aircraftId) {
        return dao.getFlightsForAircraft(aircraftId);
    }
}
