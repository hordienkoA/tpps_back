package ua.cn.stu.tpps.buyfly.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FlightDurationTest {
    private final String expected;
    private final String value;
    private Flight flight;
    private LocalDateTime departure;

    public FlightDurationTest(String expected, String value) {
        this.expected = expected;
        this.value = value;
    }

    @Parameters
    public static Collection<Object[]> hoursShift() {
        return Arrays.asList(new Object[][]{
            // {expected, value}
            {"1:00", "1:00"},
            {"10:00", "10:00"},
            {"10:10", "10:10"},
            {"3:25", "3:25"}
        });
    }

    @Before
    public void initTestData() {
        departure = LocalDateTime.of(2016, 9, 26, 21, 0);

        flight = new Flight();
        flight.setDepartureTime(departure);
    }

    /**
     * Test values of Path time
     */
    @Test
    public void getPathTimeTest() throws Exception {
        assertEquals(expected, getPathTimeTestPreparation(value));
    }

    private String getPathTimeTestPreparation(String hoursShift) {
        String[] time = hoursShift.split(":");
        LocalDateTime arrival = LocalDateTime.from(departure
            .plusHours(Integer.parseInt(time[0]))
            .plusMinutes(Integer.parseInt(time[1])));

        flight.setArrivalTime(arrival);

//        System.out.println("Sortie date: " + flight.getDepartureTime());
//        System.out.println("Arrival date: " + flight.getArrivalTime());
//        System.out.println("Diff: " + pathTime + "\n");

        return flight.getDuration();
    }
}