package br.com.cit.contacts.repository.mapper;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.repository.constants.MapperConstant;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ContactMapper {

    String SELECT_CONTACT = "SELECT * FROM CONTACT";
    String SELECT_CONTACT_BY_ID = "SELECT * FROM CONTACT WHERE ID =#{id}";
    String INSERT_CONTACT = "INSERT INTO CONTACT(NAME, CREATED_AT, UPDATED_AT, VERSION) VALUES(#{name}, #{createdAt}, #{updatedAt}, #{version})";
    String CONTACT_PROPERTY_ID = "id";
    String CONTACT_COLUMN_ID = "ID";
    String CONTACT_PROPERTY_MAILS = "mails";
    String MAIL_MAPPER_FIND_BY_CONTACT = "br.com.cit.contacts.repository.mapper.MailMapper.findByContact";

    @Select(SELECT_CONTACT)
    @Results(value = {
            @Result(property = CONTACT_PROPERTY_ID, column = CONTACT_COLUMN_ID),
            @Result(property = CONTACT_PROPERTY_MAILS, javaType = List.class, column = CONTACT_COLUMN_ID,
                    many = @Many(select = MAIL_MAPPER_FIND_BY_CONTACT, fetchType = FetchType.LAZY)),
    })
    List<Contact> findAll();

    @Select(SELECT_CONTACT_BY_ID)
    @Results(value = {
            @Result(property = CONTACT_PROPERTY_ID, column = CONTACT_COLUMN_ID),
            @Result(property = CONTACT_PROPERTY_MAILS, javaType = List.class, column = CONTACT_COLUMN_ID,
                    many = @Many(select = MAIL_MAPPER_FIND_BY_CONTACT, fetchType = FetchType.LAZY)),
    })
    List<Contact> findById(Long id);

    @Insert(INSERT_CONTACT)
    @SelectKey(before = false, keyProperty = CONTACT_PROPERTY_ID, keyColumn = CONTACT_COLUMN_ID,
            statement = MapperConstant.LAST_INSERT_ID, resultType = Long.class)
    void insert(Contact contact);

}
