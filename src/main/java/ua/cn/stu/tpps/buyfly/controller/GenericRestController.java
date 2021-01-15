package ua.cn.stu.tpps.buyfly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.GenericService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.ENTITY_CANNOT_BE_SAVED;

/**
 * Generic REST controller for common API calls
 */
@CrossOrigin
public abstract class GenericRestController<TModel, TService extends GenericService<TModel>> {
    protected static final Logger logger = LogManager.getLogger("BuyFly");
    protected static final String BUNDLE_EXCEPTION_MESSAGES = "errormessages";

    protected TService service;

    protected abstract void setService(TService service);


    /**
     * Updates entity, got from request as stringified JSON, to database if ID is present.
     * Otherwise saves it as a new entity.
     * Http method POST is used for updating.
     * Http method PUT is used for saving.
     *
     * @param model stringified JSON with entity fields
     * @return HttpStatus and saved/updated entity ID
     * @throws Exception if error occurred
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<String> save(@RequestBody String model) throws Exception {

        // Get class of object into which will JSON be deserialized
        @SuppressWarnings("unchecked")
        Class<TModel> clazz = (Class<TModel>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

        TModel entity;
        Integer savedEntityId;

        try {
            entity = new ObjectMapper().readValue(model, clazz);
            savedEntityId = service.save(entity);
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
     * Remove entity specified by ID from database.
     *
     * @param id entity unique identifier
     * @return HttpStatus
     * @throws Exception if error occurred
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> remove(@PathVariable Integer id) throws Exception {
        try {
            service.remove(id);
        } catch (ServiceException e) {
            logger.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Get all entities.
     *
     * @return collection of entities
     * @throws Exception if error occurred
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<List<TModel>> getAll() throws Exception {
        List<TModel> entities = new LinkedList<>(service.getAll());

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    /**
     * Get entity by its ID
     *
     * @param id entity unique identifier
     * @return entity
     * @throws Exception if error occurred
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<TModel> getById(@PathVariable Integer id) throws Exception {
        TModel entity = service.getById(id);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
