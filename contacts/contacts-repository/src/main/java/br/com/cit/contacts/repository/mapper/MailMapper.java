package br.com.cit.contacts.repository.mapper;

import br.com.cit.contacts.model.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MailMapper {

    String SELECT_MAILS = "SELECT * FROM MAIL";
    String SELECT_MAILS_BY_CONTACT = "SELECT * FROM MAIL WHERE CONTACT_ID = #{id}";
    String INSERT_MAIL = "INSERT INTO MAIL(MAIL) VALUES(#{mail})";

    @Select(SELECT_MAILS)
    List<Mail> findAll();

    @Select(SELECT_MAILS_BY_CONTACT)
    List<Mail> findByContact(Long id);

    @Insert(INSERT_MAIL)
    void insert(Mail mail);

}
