package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.BoardingPass;

import java.util.Collection;

public interface BoardingPassService extends GenericService<BoardingPass> {
    Collection<BoardingPass> getByCustomer(Integer customerId);

    boolean cancelBooking(Integer boardingPassId);

    Collection<BoardingPass> getBookedByCustomer(Integer customerId);

    Collection<BoardingPass> getByStatus(String status);

    Collection<BoardingPass> getByStatusAndFlight(Integer flightId, String status);

    Collection<BoardingPass> getByFlight(Integer flightId);
}
