package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.AirlineDao;
import ua.cn.stu.tpps.buyfly.domain.Airline;
import ua.cn.stu.tpps.buyfly.services.AirlineService;

/**
 * Implementation of AirlineService
 */
@Transactional
public class AirlineServiceImpl extends GenericServiceImpl<Airline, AirlineDao> implements AirlineService {

    @Autowired
    protected void setDao(AirlineDao airlineDao) {
        dao = airlineDao;
    }

    @Override
    public Airline getByAircraft(Integer aircraftId) {
        return dao.getByAircraft(aircraftId);
    }
}
