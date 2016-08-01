package br.com.cit.contacts.repository.mapper;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.model.Mail;
import br.com.cit.contacts.repository.constants.MapperConstant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MailMapper {

    String INSERT_MAIL = "INSERT INTO MAIL(MAIL, CONTACT_ID, CREATED_AT, UPDATED_AT, VERSION) VALUES(#{mail}, #{contact.id}, #{createdAt}, #{updatedAt}, #{version})";
    String UPDATE_MAIL = "UPDATE MAIL SET MAIL=#{mail}, CONTACT_ID=#{contact.id}, UPDATED_AT=#{updatedAt}, VERSION=#{version} WHERE ID=#{id}";
    String MAIL_PROPERTY_ID = "id";
    String MAIL_COLUMN_ID = "ID";
    String MAIL_PROPERTY_CONTACT = "contact";
    String MAIL_COLUMN_CONTACT_ID = "CONTACT_ID";
    String CONTACT_MAPPER_FIND_BY_ID = "br.com.cit.contacts.repository.mapper.ContactMapper.findById";

    String SELECT_MAILS = "SELECT * FROM MAIL";
    String SELECT_MAIL_BY_ID = "SELECT * FROM MAIL WHERE ID = #{id}";
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

    @Select(SELECT_MAIL_BY_ID)
    @Results(value = {
            @Result(property = MAIL_PROPERTY_ID, column = MAIL_COLUMN_ID),
            @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT, column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
            @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT, column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
    })
    Mail findById(Long id);

    @Insert(INSERT_MAIL)
    @SelectKey(before = false, keyProperty = MAIL_PROPERTY_ID, keyColumn = MAIL_COLUMN_ID,
            statement = MapperConstant.LAST_INSERT_ID, resultType = Long.class)
    void insert(Mail mail);

    @Update(UPDATE_MAIL)
    void update(Mail mail);

}
