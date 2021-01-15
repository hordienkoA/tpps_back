package ua.cn.stu.tpps.buyfly.domain;

import org.junit.Before;
import org.junit.Test;
import ua.cn.stu.tpps.buyfly.values.SeatClass;

import static org.junit.Assert.assertEquals;

public class SeatTest {
    private Seat seat;

    @Before
    public void initData() {
        seat = new Seat();
    }

    @Test
    public void seatClassTest() throws Exception {
        seat.setSeatClass(SeatClass.J);

        assertEquals(SeatClass.J.toString(), seat.getSeatClassCode());
        assertEquals("BUSINESS", seat.getSeatClass());
    }

}