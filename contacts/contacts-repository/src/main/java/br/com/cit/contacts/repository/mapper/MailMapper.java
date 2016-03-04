package br.com.cit.contacts.repository.mapper;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.Mail;
import br.com.cit.contacts.repository.constants.MapperConstant;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MailMapper {

    String INSERT_MAIL = "INSERT INTO MAIL(MAIL, CONTACT_ID, CREATED_AT, UPDATED_AT, VERSION) VALUES(#{mail}, #{contact.id}, #{createdAt}, #{updatedAt}, #{version})";
    String MAIL_PROPERTY_ID = "id";
    String MAIL_COLUMN_ID = "ID";
    String MAIL_PROPERTY_CONTACT = "contact";
    String MAIL_COLUMN_CONTACT_ID = "CONTACT_ID";
    String CONTACT_MAPPER_FIND_BY_ID = "br.com.cit.contacts.repository.mapper.ContactMapper.findById";

    String SELECT_MAILS = "SELECT * FROM MAIL";
    String SELECT_MAILS_BY_CONTACT = "SELECT * FROM MAIL WHERE CONTACT_ID = #{id}";

    @Select(SELECT_MAILS)
    @Results(value = {
            @Result(property = MAIL_PROPERTY_ID, column = MAIL_COLUMN_ID),
            @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT, column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
            @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT, column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
            @Result(property = MAIL_PROPERTY_CONTACT, javaType = Contact.class, column = MAIL_COLUMN_CONTACT_ID,
                    one = @One(select = CONTACT_MAPPER_FIND_BY_ID)),
    })
    List<Mail> findAll();

    @Select(SELECT_MAILS_BY_CONTACT)
    @Results(value = {
            @Result(property = MAIL_PROPERTY_ID, column = MAIL_COLUMN_ID),
            @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT, column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
            @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT, column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
    })
    List<Mail> findByContact(Long id);

    @Insert(INSERT_MAIL)
    @SelectKey(before = false, keyProperty = MAIL_PROPERTY_ID, keyColumn = MAIL_COLUMN_ID,
            statement = MapperConstant.LAST_INSERT_ID, resultType = Long.class)
    void insert(Mail mail);

}
