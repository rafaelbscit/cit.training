package br.com.cit.contacts.api.mapper;

import br.com.cit.contacts.model.Contact;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ContactMapper {


    String SELECT_CONTACT = "SELECT * FROM CONTACT";
    String SELECT_CONTACT_BY_ID = "SELECT * FROM CONTACT WHERE ID =#{id}";
    String INSERT_CONTACT = "INSERT INTO CONTACT VALUES(#{name})";

    @Select(SELECT_CONTACT)
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "mails", javaType = List.class, column = "ID",
                    many = @Many(select = "br.com.cit.contacts.api.mapper.MailMapper.findByContact", fetchType = FetchType.EAGER)),
    })
    List<Contact> findAll();

    @Select(SELECT_CONTACT_BY_ID)
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "mails", javaType = List.class, column = "ID",
                    many = @Many(select = "br.com.cit.contacts.api.mapper.MailMapper.findByContact", fetchType = FetchType.EAGER)),
    })
    List<Contact> findById(Long id);

    @Insert(INSERT_CONTACT)
    void insert(Contact contact);

}
