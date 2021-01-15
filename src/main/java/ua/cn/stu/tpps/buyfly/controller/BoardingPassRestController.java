package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.BoardingPass;
import ua.cn.stu.tpps.buyfly.domain.Flight;
import ua.cn.stu.tpps.buyfly.domain.Seat;
import ua.cn.stu.tpps.buyfly.domain.User;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.BoardingPassService;
import ua.cn.stu.tpps.buyfly.services.FlightService;
import ua.cn.stu.tpps.buyfly.services.SeatService;
import ua.cn.stu.tpps.buyfly.services.UserService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.util.Collection;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.ENTITY_CANNOT_BE_SAVED;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.BoardingPass} entities through API calls
 */
@RestController
@RequestMapping("/api/boardingPasses")
public class BoardingPassRestController extends GenericRestController<BoardingPass, BoardingPassService> {
    private UserService userService;
    private FlightService flightService;
    private SeatService seatService;

    @Override
    @Autowired
    protected void setService(BoardingPassService service) {
        this.service = service;
    }

    @Autowired
    protected void setUserService(UserService service) {
        this.userService = service;
    }

    @Autowired
    protected void setFlightService(FlightService service) {
        this.flightService = service;
    }

    @Autowired
    protected void setService(SeatService service) {
        this.seatService = service;
    }

    @Override
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {
        String errorMessage = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_SAVED);
        ResponseEntity<String> responseBadRequest = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        Integer userId, seatId, flightId;
        Flight flight;
        User user;
        Seat seat;

        try {
            JSONObject jsonObject = new JSONObject(model);

            userId = jsonObject.getInt("userId");
            seatId = jsonObject.getInt("seatId");
            flightId = jsonObject.getInt("flightId");

            flight = flightService.getById(flightId);
            user = userService.getById(userId);
            seat = seatService.getById(seatId);
        } catch (ServiceException e) {
            logger.error(errorMessage, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(errorMessage, e);
            return responseBadRequest;
        }

        if (flight == null || user == null || seat == null) {
            return responseBadRequest;
        }

        Integer savedEntityId;

        try {
            BoardingPass boardingPass = new ObjectMapper().readValue(model, BoardingPass.class);
            boardingPass.setFlight(flight);
            boardingPass.setSeat(seat);
            boardingPass.setCustomer(user);

            savedEntityId = service.save(boardingPass);
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
     * Get collection of boarding passes for customer.
     *
     * @param customerId customer unique identifier
     * @param booked     (optional) if searched boarding passes have booked status. If not present - get all boarding passes
     * @return HttpStatus and collection of boarding passes as JSON object
     */
    @RequestMapping(value = "/getByCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<BoardingPass>> getByCustomer(@RequestParam Integer customerId,
                                                                  @RequestParam(required = false) boolean booked) {
        Collection<BoardingPass> boardingPasses;

        if (booked) {
            boardingPasses = service.getBookedByCustomer(customerId);
        } else {
            boardingPasses = service.getByCustomer(customerId);
        }

        if (boardingPasses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(boardingPasses, HttpStatus.OK);
    }


    /**
     * Cancels booking for boarding pass specified by ID.
     *
     * @param boardingPassId boarding pass unique identifier
     * @return HttpStatus and collection of boarding passes as JSON object
     */
    @RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity cancelBooking(@RequestParam Integer boardingPassId) {
        boolean result = service.cancelBooking(boardingPassId);

        return new ResponseEntity(result ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);
    }


    /**
     * Get collection of boarding passes by specified params: status or flight or both.
     * If no parameters provided responds with HttpStatus.BAD_REQUEST
     *
     * @param status   boarding pass - BOOKED, PAID or CANCELLED
     * @param flightId flight unique identifier
     * @return HttpStatus and collection of boarding passes as JSON object
     */
    @RequestMapping(value = "/getByParams", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Collection<BoardingPass>> getByParams(@RequestParam(required = false) String status,
                                                                @RequestParam(required = false) Integer flightId) {
        if (status == null && flightId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<BoardingPass> boardingPasses;

        if (status != null && flightId != null) {
            boardingPasses = service.getByStatusAndFlight(flightId, status);
        } else if (status != null) {
            boardingPasses = service.getByStatus(status);
        } else {
            boardingPasses = service.getByFlight(flightId);
        }

        if (boardingPasses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(boardingPasses, HttpStatus.OK);
    }
}
