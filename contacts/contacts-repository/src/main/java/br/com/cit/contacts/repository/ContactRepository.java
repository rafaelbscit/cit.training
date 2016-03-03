package br.com.cit.contacts.repository;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.Mail;
import br.com.cit.contacts.repository.exception.RepositoryException;
import br.com.cit.contacts.repository.mapper.ContactMapper;
import br.com.cit.contacts.repository.mapper.MailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactRepository.class);

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private MailMapper mailMapper;

    public List<Contact> findAll() throws RepositoryException {
        LOGGER.info("Find all contacts!");
        return contactMapper.findAll();
    }

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

}
