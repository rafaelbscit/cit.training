package br.com.cit.contacts.api.service;

import br.com.cit.contacts.api.exception.RepositoryException;
import br.com.cit.contacts.api.exception.ServiceException;
import br.com.cit.contacts.api.repository.ContactRepository;
import br.com.cit.contacts.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() throws ServiceException {

        try {
            return contactRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
