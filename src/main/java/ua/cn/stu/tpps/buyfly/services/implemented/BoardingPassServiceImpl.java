package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.BoardingPassDao;
import ua.cn.stu.tpps.buyfly.domain.BoardingPass;
import ua.cn.stu.tpps.buyfly.services.BoardingPassService;
import ua.cn.stu.tpps.buyfly.values.BoardingPassStatus;

import java.util.Collection;

@Transactional
public class BoardingPassServiceImpl extends GenericServiceImpl<BoardingPass, BoardingPassDao> implements BoardingPassService {

    @Autowired
    protected void setDao(BoardingPassDao boardingPassDao) {
        dao = boardingPassDao;
    }

    @Override
    public Collection<BoardingPass> getByCustomer(Integer customerId) {
        return dao.getByCustomer(customerId);
    }

    @Override
    public boolean cancelBooking(Integer boardingPassId) {
        dao.setStatus(BoardingPassStatus.CANCELLED.toString(), boardingPassId);
        return true;
    }

    @Override
    public Collection<BoardingPass> getBookedByCustomer(Integer customerId) {
        return dao.getByCustomer(customerId);
    }

    @Override
    public Collection<BoardingPass> getByStatus(String status) {
        return dao.getByStatus(status);
    }

    @Override
    public Collection<BoardingPass> getByStatusAndFlight(Integer flightId, String status) {
        return dao.getByStatusAndFlight(flightId, status);
    }

    @Override
    public Collection<BoardingPass> getByFlight(Integer flightId) {
        return dao.getByFlight(flightId);
    }
}
