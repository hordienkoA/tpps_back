package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Airline;
import ua.cn.stu.tpps.buyfly.domain.Flight;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.AircraftService;
import ua.cn.stu.tpps.buyfly.services.AirlineService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.util.Collection;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.*;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.Aircraft} entities through API calls
 */
@RestController
@RequestMapping("/api/aircraft")
public class AircraftRestController extends GenericRestController<Aircraft, AircraftService> {
    private AirlineService airlineService;

    @Override
    @Autowired
    protected void setService(AircraftService service) {
        this.service = service;
    }

    @Autowired
    private void setAirlineService(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @Override
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {
        String cannotSaveEntityMsg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED);

        ResponseEntity<String> responseBadRequest = new ResponseEntity<>(cannotSaveEntityMsg, HttpStatus.BAD_REQUEST);

        Integer airlineId = null;
        Airline airline;

        try {
            airlineId = new JSONObject(model).getInt("airlineId");
            airline = airlineService.getById(airlineId);
        } catch (ServiceException e) {
            String failedToGetAirlineMsg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, FAILED_TO_GET_AIRLINE, airlineId);
            logger.error(failedToGetAirlineMsg, e);
            return new ResponseEntity<>(failedToGetAirlineMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(cannotSaveEntityMsg, e);
            return responseBadRequest;
        }

        if (airline == null) {
            return responseBadRequest;
        }

        Integer savedEntityId;

        try {
            Aircraft aircraft = new ObjectMapper().readValue(model, Aircraft.class);
            aircraft.setAirline(airline);
            savedEntityId = service.save(aircraft);
        } catch (ServiceException e) {
            logger.error(e);
            return new ResponseEntity<>(ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ERROR_WHILE_SAVING_ENTITY),
                HttpStatus.INTERNAL_SERVER_ERROR);
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
     * Get collection of flights for aircraft specified by ID.
     *
     * @param aircraftId aircraft unique identifier
     * @return HttpStatus and collection of flights as JSON object
     */
    @RequestMapping(value = "/getFlights", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Flight>> getFlights(@RequestParam Integer aircraftId) {
        Collection<Flight> flights = service.getFlightsForAircraft(aircraftId);

        if (flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(flights, HttpStatus.OK);
    }
}
