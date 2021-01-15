package ua.cn.stu.tpps.buyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.domain.User;
import ua.cn.stu.tpps.buyfly.services.UserService;

import java.util.Collection;

/**
 * REST controller for interacting with {@link ua.cn.stu.tpps.buyfly.domain.User} entities through API calls
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController extends GenericRestController<User, UserService> {

    @Override
    @Autowired
    protected void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getByEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        User user = service.getByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByRoleType", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<User>> getByRoleType(@RequestParam String role) {
        Collection<User> users = service.getByRoleType(role);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByFlight", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<User>> getByFlight(@RequestParam Integer flightId) {
        Collection<User> users = service.getByFlight(flightId);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/setEnabled", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setEnabled(@RequestParam Integer userId,
                                     @RequestParam boolean enabled) {
        service.setEnabled(userId, enabled);
        return new ResponseEntity(HttpStatus.OK);
    }

}
