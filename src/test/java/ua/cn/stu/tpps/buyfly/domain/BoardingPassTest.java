package ua.cn.stu.tpps.buyfly.domain;

import org.junit.Before;
import org.junit.Test;
import ua.cn.stu.tpps.buyfly.values.BoardingPassStatus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BoardingPassTest {
    private BoardingPass boardingPass;

    @Before
    public void initData() {
        boardingPass = new BoardingPass();
    }

    @Test
    public void boardingPassStatusTest() throws Exception {
        // Test status BOOKED
        boardingPass.setStatus(BoardingPassStatus.BOOKED);
        assertEquals(BoardingPassStatus.BOOKED.toString(), boardingPass.getStatus());
        assertNotEquals(BoardingPassStatus.CANCELLED.toString(), boardingPass.getStatus());

        // Test status CANCELLED
        boardingPass.setStatus(BoardingPassStatus.CANCELLED);
        assertEquals(BoardingPassStatus.CANCELLED.toString(), boardingPass.getStatus());

        // Test status PAID
        boardingPass.setStatus(BoardingPassStatus.PAID);
        assertEquals(BoardingPassStatus.PAID.toString(), boardingPass.getStatus());
    }
}