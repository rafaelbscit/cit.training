package br.com.cit.contacts.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.Mail;
import br.com.cit.contacts.repository.exception.RepositoryException;
import br.com.cit.contacts.repository.mapper.ContactMapper;
import br.com.cit.contacts.repository.mapper.MailMapper;

@CacheConfig(cacheNames = { "contacts:ContactRepository" })
@Repository
public class ContactRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactRepository.class);

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private MailMapper mailMapper;

    @Cacheable
    public List<Contact> findAll() throws RepositoryException {
        LOGGER.info("Find all contacts!");
        return contactMapper.findAll();
    }

    @Cacheable
    public Contact findByName(String name) throws RepositoryException {
        LOGGER.info("Find contact by name [{}]!", name);
        return contactMapper.findByName(name);
    }

    @Cacheable
    public Contact findById(Long id) throws RepositoryException {
        LOGGER.info("Find contact by id [{}]!", id);
        return contactMapper.findById(id);
    }

    @CacheEvict(allEntries = true)
    public void insert(Contact contact) throws RepositoryException {
        try {
            LOGGER.info("Insert new contact [{}]!", contact);
            contact.initEntity();
            contactMapper.insert(contact);

            if (contact.getMails() != null) {
                for (Mail mail : contact.getMails()) {

                    mail.setContact(contact);
                    mail.initEntity();
                    mailMapper.insert(mail);
                }
            }

        } catch (DuplicateKeyException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new RepositoryException(e);
        }
    }

    @CacheEvict(allEntries = true)
    public void update(Contact contact) throws RepositoryException {
        try {
            LOGGER.info("Update contact [{}]!", contact);
            contact.updateEntity();
            contactMapper.update(contact);

            if (contact.getMails() != null) {
                for (Mail mail : contact.getMails()) {

                    mail.setContact(contact);
                    mail.initEntity();
                    mailMapper.insert(mail);
                }
            }

        } catch (DuplicateKeyException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new RepositoryException(e);
        }
    }

}
