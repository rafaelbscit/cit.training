package br.com.cit.contacts.repository.mapper;

import br.com.cit.contacts.model.Contact;
import br.com.cit.contacts.repository.constants.MapperConstant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContactMapper {

    String SELECT_CONTACT = "SELECT * FROM CONTACT";
    String SELECT_CONTACT_BY_NAME = "SELECT * FROM CONTACT WHERE NAME =#{name}";
    String SELECT_CONTACT_BY_ID = "SELECT * FROM CONTACT WHERE ID =#{id}";
    String INSERT_CONTACT = "INSERT INTO CONTACT(NAME, CREATED_AT, UPDATED_AT, VERSION) VALUES(#{name}, #{createdAt}, #{updatedAt}, #{version})";
    String UPDATE_CONTACT = "UPDATE CONTACT SET NAME=#{name}, UPDATED_AT=#{updatedAt}, VERSION=#{version} WHERE ID =#{id}";
    String CONTACT_PROPERTY_ID = "id";
    String CONTACT_COLUMN_ID = "ID";
    String CONTACT_PROPERTY_MAILS = "mails";
    String MAIL_MAPPER_FIND_BY_CONTACT = "br.com.cit.contacts.repository.mapper.MailMapper.findByContact";

    @Select(SELECT_CONTACT)
    @Results(value = {
            @Result(property = CONTACT_PROPERTY_ID,
                            column = CONTACT_COLUMN_ID),
            @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT,
                            column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
            @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT,
                            column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
            @Result(property = CONTACT_PROPERTY_MAILS, javaType = List.class,
                            column = CONTACT_COLUMN_ID,
                            many = @Many(select = MAIL_MAPPER_FIND_BY_CONTACT)),
    })
    List<Contact> findAll();

    @Select(SELECT_CONTACT_BY_ID)
    @Results(value = {
            @Result(property = CONTACT_PROPERTY_ID,
                            column = CONTACT_COLUMN_ID),
            @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT,
                            column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
            @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT,
                            column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
            @Result(property = CONTACT_PROPERTY_MAILS, javaType = List.class,
                            column = CONTACT_COLUMN_ID,
                            many = @Many(select = MAIL_MAPPER_FIND_BY_CONTACT)),
    })
    Contact findById(Long id);

    @Select(SELECT_CONTACT_BY_NAME)
    @Results(value = {
                    @Result(property = CONTACT_PROPERTY_ID,
                                    column = CONTACT_COLUMN_ID),
                    @Result(property = MapperConstant.ENTITY_PROPERTY_CREATED_AT,
                                    column = MapperConstant.ENTITY_COLUMN_CREATED_AT),
                    @Result(property = MapperConstant.ENTITY_PROPERTY_UPDATED_AT,
                                    column = MapperConstant.ENTITY_COLUMN_UPDATED_AT),
                    @Result(property = CONTACT_PROPERTY_MAILS, javaType = List.class,
                                    column = CONTACT_COLUMN_ID,
                                    many = @Many(select = MAIL_MAPPER_FIND_BY_CONTACT)),
    })
    List<Contact> findByName(String name);

    @Insert(INSERT_CONTACT)
    @SelectKey(before = false,
                    keyProperty = CONTACT_PROPERTY_ID,
                    keyColumn = CONTACT_COLUMN_ID,
                    statement = MapperConstant.LAST_INSERT_ID,
                    resultType = Long.class)
    void insert(Contact contact);

    @Update(UPDATE_CONTACT)
    void update(Contact contact);

}
