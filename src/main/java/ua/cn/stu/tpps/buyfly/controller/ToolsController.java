package ua.cn.stu.tpps.buyfly.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.cn.stu.tpps.buyfly.domain.Airline;
import ua.cn.stu.tpps.buyfly.services.AirlineService;

/**
 * Tools for initializing DB for use.
 */
@RestController
@RequestMapping("/api/tools")
public class ToolsController extends GenericRestController<Airline, AirlineService> {
    public static final Logger logger = LogManager.getLogger("BuyFly");

    @Override
    @Autowired
    protected void setService(AirlineService service) {
        this.service = service;
    }


    /**
     * Save test airline entity to generate tables in DB first time.
     *
     * @return HttpStatus
     */
    @RequestMapping(value = "/db/init", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity initDB() {

        // Create test airline entity to be saved
        Airline airline = new Airline();
        airline.setName("Test Airline");
        airline.setIATA("TT");
        airline.setICAO("TTA");

        Integer savedEntityId = null;

        try {
            savedEntityId = service.save(airline);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (savedEntityId == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("DB successfully generated", HttpStatus.OK);
    }
}
