package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Airport;
import ua.cn.stu.tpps.buyfly.domain.Flight;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.AircraftService;
import ua.cn.stu.tpps.buyfly.services.AirportService;
import ua.cn.stu.tpps.buyfly.services.FlightService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.LinkedList;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.ENTITY_CANNOT_BE_SAVED;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.Flight} entities through API calls
 */
@RestController
@RequestMapping("/api/flights")
public class FlightsRestController extends GenericRestController<Flight, FlightService> {
    @SuppressWarnings("FieldCanBeLocal")
    private final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private AirportService airportService;
    private AircraftService aircraftService;

    @Override
    @Autowired
    protected void setService(FlightService service) {
        this.service = service;
    }

    @Autowired
    protected void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    @Autowired
    protected void setAircraftService(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    @Override
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {
        String errorMessage = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED);
        ResponseEntity<String> responseBadRequest = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        Integer originAirportId, destinationAirportId, aircraftId;
        Airport originAirport, destinationAirport;
        Aircraft aircraft;

        try {
            JSONObject jsonObject = new JSONObject(model);

            originAirportId = jsonObject.getInt("originAirportId");
            destinationAirportId = jsonObject.getInt("destinationAirportId");
            aircraftId = jsonObject.getInt("aircraftId");

            originAirport = airportService.getById(originAirportId);
            destinationAirport = airportService.getById(destinationAirportId);
            aircraft = aircraftService.getById(aircraftId);
        } catch (ServiceException e) {
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            return responseBadRequest;
        }

        if (originAirport == null || destinationAirport == null || aircraft == null) {
            return responseBadRequest;
        }

        Integer savedEntityId;

        try {
            Flight flight = new ObjectMapper().readValue(model, Flight.class);
            flight.setOriginAirport(originAirport);
            flight.setDestinationAirport(destinationAirport);
            flight.setAircraft(aircraft);

            savedEntityId = service.save(flight);
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

    @RequestMapping(value = "/getByCountries", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getByCountries(@RequestParam String origin,
                                                             @RequestParam String destination) {
        Collection<Flight> flights = service.getByCountries(origin, destination);

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByCities", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getByCities(@RequestParam Integer origin,
                                                          @RequestParam Integer destination) {
        Collection<Flight> flights = service.getByDirection(origin, destination);

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getByCustomer(@RequestParam Integer customerId,
                                                            @RequestParam(defaultValue = "all") String type) {
        Collection<Flight> flights = new LinkedList<>();

        switch (type) {
            case "all":
                flights = service.getCurrentFlightsByCustomer(customerId);
                flights.addAll(service.getPreviousFlightsByCustomer(customerId));
                break;
            case "current":
                flights = service.getCurrentFlightsByCustomer(customerId);
                break;
            case "completed":
                flights = service.getPreviousFlightsByCustomer(customerId);
                break;
        }

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByAirports", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getByAirports(@RequestParam Integer originAirportId,
                                                            @RequestParam Integer destinationAirportId) {
        Collection<Flight> flights = service.getByAirports(originAirportId, destinationAirportId);

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBetweenDates", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getBetweenDates(@RequestParam String departure,
                                                              @RequestParam String arrival) {
        LocalDateTime departureTime;
        LocalDateTime arrivalTime;
        try {
            departureTime = LocalDateTime.parse(departure, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            arrivalTime = LocalDateTime.parse(arrival, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<Flight> flights = service.getBetweenDates(departureTime, arrivalTime);

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
