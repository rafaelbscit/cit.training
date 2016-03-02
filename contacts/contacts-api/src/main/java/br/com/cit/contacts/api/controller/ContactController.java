package br.com.cit.contacts.api.controller;

import br.com.cit.contacts.api.service.ContactService;
import br.com.cit.contacts.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/contact/")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Contact> getAll() {
        LOGGER.info("Retrieve all contacts!");
        List<Contact> contacts = contactService.findAll();

        LOGGER.info("Found {} contacts!", contacts.size());
        return contacts;
    }

}
