package ua.cn.stu.tpps.buyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.Airline;
import ua.cn.stu.tpps.buyfly.services.AirlineService;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.Airline} entities through API calls
 */
@RestController
@RequestMapping("/api/airlines")
public class AirlineRestController extends GenericRestController<Airline, AirlineService> {

    @Override
    @Autowired
    protected void setService(AirlineService service) {
        this.service = service;
    }


    /**
     * Get airline for aircraft specified by ID.
     *
     * @param aircraftId aircraft unique identifier
     * @return HttpStatus and {@link Airline} JSON object
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Airline> getByAircraft(@RequestParam Integer aircraftId) {
        Airline airline = service.getByAircraft(aircraftId);

        if (airline == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(airline, HttpStatus.OK);
    }
}
