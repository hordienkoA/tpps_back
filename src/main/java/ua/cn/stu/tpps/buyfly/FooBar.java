package ua.cn.stu.tpps.buyfly;

import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Airline;
import ua.cn.stu.tpps.buyfly.domain.Seat;
import ua.cn.stu.tpps.buyfly.services.AircraftService;
import ua.cn.stu.tpps.buyfly.services.AirlineService;
import ua.cn.stu.tpps.buyfly.services.SeatService;
import ua.cn.stu.tpps.buyfly.values.SeatClass;

import java.util.LinkedList;
import java.util.List;

public class FooBar {

    public void testHibernate() {
        Airline airline = new Airline();
        airline.setName("Test Airlines");
        airline.setIATA("TE");
        airline.setICAO("TST");

        Aircraft aircraft = new Aircraft();
        aircraft.setName("Test");
        aircraft.setModel("737");
        aircraft.setAirline(airline);

        Seat seat = new Seat();
        seat.setNumber("2323");
        seat.setPrice(23.34);
        seat.setSeatClass(SeatClass.F);
        seat.setTaken(true);

        Seat seat1 = new Seat();
        seat1.setNumber("2323");
        seat1.setPrice(23.34);
        seat1.setSeatClass(SeatClass.F);
        seat1.setTaken(true);

        List<Seat> s = new LinkedList<>();
        s.add(seat1);
        s.add(seat);

        aircraft.setSeats(s);

//        AirlineService airlineService = ServiceFactory.DEFAULT.getAirlineService();
//        AircraftService aircraftService = ServiceFactory.DEFAULT.getAircraftService();
//        SeatService seatService = ServiceFactory.DEFAULT.getSeatService();
//
//        try {
//            airlineService.save(airline);
//            seatService.save(seat);
//            seatService.save(seat1);
//            aircraftService.save(aircraft);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}