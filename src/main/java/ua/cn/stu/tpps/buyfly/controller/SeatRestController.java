package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.Aircraft;
import ua.cn.stu.tpps.buyfly.domain.Seat;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.AircraftService;
import ua.cn.stu.tpps.buyfly.services.SeatService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.util.Collection;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.ENTITY_CANNOT_BE_SAVED;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.Seat} entities through API calls
 */
@RestController
@RequestMapping("/api/seats")
public class SeatRestController extends GenericRestController<Seat, SeatService> {
    private AircraftService aircraftService;

    @Override
    @Autowired
    protected void setService(SeatService service) {
        this.service = service;
    }

    @Autowired
    protected void setAircraftService(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    @Override
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {
        String errorMessage = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED);
        ResponseEntity<String> responseBadRequest = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        Integer aircraftId;
        Aircraft aircraft;

        try {
            aircraftId = new JSONObject(model).getInt("aircraftId");
            aircraft = aircraftService.getById(aircraftId);
        } catch (ServiceException e) {
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            return responseBadRequest;
        }

        if (aircraft == null) {
            return responseBadRequest;
        }

        Integer savedSeatId, savedAircraftId;

        try {
            Seat seat = new ObjectMapper().readValue(model, Seat.class);
            aircraft.addSeats(seat);
            savedSeatId = service.save(seat);
            savedAircraftId = aircraftService.save(aircraft);
        } catch (ServiceException e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(
                ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED), HttpStatus.BAD_REQUEST);
        }

        if (savedSeatId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (savedAircraftId == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedSeatId.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByClass", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Seat>> getByClass(@RequestParam String seatClass,
                                                       @RequestParam Integer aircraftId) {
        Collection<Seat> seats = service.getByClass(aircraftId, seatClass);

        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFree", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Seat>> getFree(@RequestParam Integer aircraftId,
                                                    @RequestParam(required = false) Double min,
                                                    @RequestParam(required = false) Double max) {
        Collection<Seat> seats;

        if (min != null && max != null) {
            if (min < 0 || max < 0 || max < min) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            seats = service.getFreeByPriceRange(aircraftId, min, max);
        } else {
            seats = service.getFreeSeats(aircraftId);
        }

        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
}
