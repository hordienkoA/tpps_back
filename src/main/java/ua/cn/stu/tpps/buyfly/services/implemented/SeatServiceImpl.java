package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.SeatDao;
import ua.cn.stu.tpps.buyfly.domain.Seat;
import ua.cn.stu.tpps.buyfly.services.SeatService;

import java.util.Collection;

/**
 * Implementation of SeatService
 */
@Transactional
public class SeatServiceImpl extends GenericServiceImpl<Seat, SeatDao> implements SeatService {

    @Autowired
    protected void setDao(SeatDao seatDao) {
        dao = seatDao;
    }

    @Override
    public Collection<Seat> getByClass(Integer aircraftId, String seatClass) {
        return dao.getByClass(aircraftId, seatClass);
    }

    @Override
    public Collection<Seat> getFreeSeats(Integer aircraftId) {
        return dao.getFreeSeats(aircraftId);
    }

    @Override
    public Collection<Seat> getFreeByPriceRange(Integer aircraftId, double lowestPrice, double highestPrice) {
        return dao.getFreeByPriceRange(aircraftId, lowestPrice, highestPrice);
    }
}
