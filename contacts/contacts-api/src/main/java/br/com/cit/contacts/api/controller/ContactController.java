package br.com.cit.contacts.api.controller;

import br.com.cit.contacts.api.constants.RestControllerConstant;
import br.com.cit.contacts.api.service.ContactService;
import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.response.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contact/")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @ApiOperation("getAll")
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = RestControllerConstant.PRODUCES)
    @ResponseBody
    public ResponseEntity getAll() {
        LOGGER.info("Retrieve all contacts!");
        List<Contact> contacts = contactService.findAll();

        LOGGER.info("Found {} contacts!", contacts.size());

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity(status.value(), status.getReasonPhrase(), contacts);
    }

    @ApiOperation("addNewContact")
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = RestControllerConstant.PRODUCES,
            consumes = RestControllerConstant.CONSUMES)
    @ResponseBody
    public ResponseEntity addNewContact(@RequestBody Contact contact) {
        LOGGER.info("Add new contact: [{}]!", contact);
        Contact newContact = contactService.insert(contact);

        LOGGER.info("Contact added with id[{}]!", contact.getId());

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity(status.value(), status.getReasonPhrase(), newContact);
    }

}
