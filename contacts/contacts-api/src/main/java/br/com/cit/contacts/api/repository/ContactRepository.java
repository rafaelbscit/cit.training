package br.com.cit.contacts.api.repository;

import br.com.cit.contacts.api.exception.RepositoryException;
import br.com.cit.contacts.api.mapper.ContactMapper;
import br.com.cit.contacts.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private ContactMapper contactMapper;

    public List<Contact> findAll() throws RepositoryException {
        try {
            return contactMapper.findAll();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

}
