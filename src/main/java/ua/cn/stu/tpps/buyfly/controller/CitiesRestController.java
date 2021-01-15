package ua.cn.stu.tpps.buyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.City;
import ua.cn.stu.tpps.buyfly.services.CityService;

import java.util.Collection;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.City} entities through API calls
 */
@RestController
@RequestMapping("/api/cities")
public class CitiesRestController extends GenericRestController<City, CityService> {

    @Override
    @Autowired
    protected void setService(CityService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getByCountry", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<City>> getByCountries(@RequestParam String country) {
        Collection<City> cities = service.getCitiesByCountry(country);

        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByAirline", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<City>> getByAirline(@RequestParam String airline) {
        Collection<City> cities = service.getCitiesByAirline(airline);

        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<City> getByName(@RequestParam String name) {
        City city = service.getCity(name);

        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
