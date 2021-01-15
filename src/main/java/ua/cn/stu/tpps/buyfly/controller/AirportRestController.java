package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.Airport;
import ua.cn.stu.tpps.buyfly.domain.City;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.AirportService;
import ua.cn.stu.tpps.buyfly.services.CityService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.util.Collection;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.ENTITY_CANNOT_BE_SAVED;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.Airport} entities through API calls
 */
@RestController
@RequestMapping("/api/airports")
public class AirportRestController extends GenericRestController<Airport, AirportService> {
    private CityService cityService;

    @Override
    @Autowired
    protected void setService(AirportService service) {
        this.service = service;
    }

    @Autowired
    protected void setCityService(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {
        String errorMessage = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED);
        ResponseEntity<String> responseBadRequest = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        Integer cityId;
        City city;

        try {
            cityId = new JSONObject(model).getInt("cityId");
            city = cityService.getById(cityId);
        } catch (ServiceException e) {
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            return responseBadRequest;
        }

        if (city == null) {
            return responseBadRequest;
        }

        Integer savedEntityId;

        try {
            Airport airport = new ObjectMapper().readValue(model, Airport.class);
            airport.setCity(city);
            savedEntityId = service.save(airport);
        } catch (ServiceException e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(
                ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED), HttpStatus.BAD_REQUEST);
        }

        if (savedEntityId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(savedEntityId.toString(), HttpStatus.OK);
    }

    /**
     * Get collection of airports in specified country.
     *
     * @param country name of the country
     * @return HttpStatus and collection of airports as JSON object
     */
    @RequestMapping(value = "/getByCountry", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Airport>> getByCountry(@RequestParam String country) {
        Collection<Airport> airports = service.getByCountry(country);

        if (airports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(airports, HttpStatus.OK);
    }


    /**
     * Get collection of airports in specified city.
     *
     * @param city name of the city
     * @return HttpStatus and collection of airports as JSON object
     */
    @RequestMapping(value = "/getByCity", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Airport>> getByCity(@RequestParam String city) {
        Collection<Airport> airports = service.getByCity(city);

        if (airports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(airports, HttpStatus.OK);
    }
}
