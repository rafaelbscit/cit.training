package br.com.cit.contacts.api.service;

import br.com.cit.contacts.api.exception.ServiceException;
import br.com.cit.contacts.api.service.validation.ContactValidation;
import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.repository.ContactRepository;
import br.com.cit.contacts.repository.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
            LOGGER.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }

    public List<Contact> findByName(String name) throws ServiceException {
        try {
            return contactRepository.findByName(name);

        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }

    public Contact findById(Long id) throws ServiceException {
        try {
            return contactRepository.findById(id);

        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Contact insert(Contact contact) throws ServiceException {
        contactValidation.validate(contact);
        try {
            contactRepository.insert(contact);
            return contact;
        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Contact update(Contact contact) throws ServiceException {
        contactValidation.validate(contact);
        try {
            contactRepository.update(contact);
            return contact;
        } catch (RepositoryException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new ServiceException(e);
        }
    }

}
