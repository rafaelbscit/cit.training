package br.com.cit.contacts.api.controller;

import br.com.cit.contacts.api.constants.RestControllerConstant;
import br.com.cit.contacts.api.exception.ServiceException;
import br.com.cit.contacts.api.exception.ValidationException;
import br.com.cit.contacts.api.service.ContactService;
import br.com.cit.contacts.model.Contact;
import com.google.common.base.Throwables;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contact/")
public class ContactController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @ApiOperation("getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = RestControllerConstant.PRODUCES)
    @ResponseBody
    public ResponseEntity<List<Contact>> getAll() {
        LOGGER.info("Retrieve all contacts!");
        List<Contact> contacts = contactService.findAll();

        if (contacts == null || contacts.isEmpty()) {
            LOGGER.info("Not found contacts!");
            return buildResponseErro(HttpStatus.NO_CONTENT);
        }

        LOGGER.info("Found {} contacts!", contacts.size());
        return buildResponseSuccess(contacts, HttpStatus.OK);
    }

    @ApiOperation("addNewContact")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 304, message = "Not Modified"),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = RestControllerConstant.PRODUCES,
            consumes = RestControllerConstant.CONSUMES)
    @ResponseBody
    public ResponseEntity<Contact> addNewContact(@RequestBody Contact contact) {

        LOGGER.info("Add new contact: [{}]!", contact);
        try {
            Contact newContact = contactService.insert(contact);
            LOGGER.info("Contact added with id[{}]!", newContact.getId());

            return buildResponseSuccess(newContact, HttpStatus.CREATED);
        } catch (ValidationException e) {

            Throwable rootCause = Throwables.getRootCause(e);
            LOGGER.error(e.getLocalizedMessage(), rootCause);
            return buildResponseErro(rootCause, HttpStatus.BAD_REQUEST);
        } catch (ServiceException e) {

            Throwable rootCause = Throwables.getRootCause(e);
            LOGGER.error(e.getLocalizedMessage(), rootCause);
            return buildResponseErro(rootCause, HttpStatus.NOT_MODIFIED);
        }
    }

}
