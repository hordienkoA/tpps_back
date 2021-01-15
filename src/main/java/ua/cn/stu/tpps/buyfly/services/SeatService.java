package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.Seat;

import java.util.Collection;

public interface SeatService extends GenericService<Seat> {
    Collection<Seat> getByClass(Integer aircraftId, String seatClass);

    Collection<Seat> getFreeSeats(Integer aircraftId);

    Collection<Seat> getFreeByPriceRange(Integer aircraftId, double lowestPrice, double highestPrice);
}
