package ua.cn.stu.tpps.buyfly.domain;

import org.junit.Before;
import org.junit.Test;
import ua.cn.stu.tpps.buyfly.values.Country;

import static org.junit.Assert.assertEquals;

public class CityTest {
    private City city;

    @Before
    public void initData() {
        city = new City();
        city.setName("Dubai");
    }

    @Test
    public void setGetCountryTest() throws Exception {
        city.setCountry(Country.ARE.toString());

        assertEquals("United Arab Emirates", city.getCountry());
        assertEquals("ARE", city.getCountryCode());
    }

    @Test
    public void testCountryGetFullName() throws Exception {
        assertEquals("United States", Country.USA.getFullName());
        assertEquals("Poland", Country.POL.getFullName());
        assertEquals("UKR", Country.UKR.toString());
    }

}