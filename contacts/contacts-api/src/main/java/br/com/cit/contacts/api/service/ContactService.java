package br.com.cit.contacts.api.service;

import br.com.cit.contacts.api.exception.ServiceException;
import br.com.cit.contacts.api.service.validation.ContactValidation;
import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.repository.ContactRepository;
import br.com.cit.contacts.repository.exception.RepositoryException;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactValidation contactValidation;

    public List<Contact> findAll() throws ServiceException {
        try {
            return contactRepository.findAll();

        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage(), Throwables.getRootCause(e));
            throw new ServiceException(e);
        }
    }

    public Contact insert(Contact contact) {
        try {
            contactValidation.validate(contact);

            contactRepository.insert(contact);
            return contact;
        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage(), Throwables.getRootCause(e));
            throw new ServiceException(e);
        }
    }

}
