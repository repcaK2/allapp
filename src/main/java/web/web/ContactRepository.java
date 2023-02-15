package web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Contact> getAll(){
        return jdbcTemplate.query("SELECT id, number, name, surname FROM contacts",
                BeanPropertyRowMapper.newInstance(Contact.class));
    }

    public Contact getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, number, name, surname FROM contacts WHERE " + "id = ?",
                BeanPropertyRowMapper.newInstance(Contact.class), id);
    }

    public String save(List<Contact> contacts){
        contacts.forEach(contact -> jdbcTemplate.update("INSERT INTO contacts(number, name, surname) VALUES(?, ?, ?)",
                contact.getNumber(), contact.getName(), contact.getSurname()));

        return "contactsadd";
    }

    public String delete(int id){
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?", id);
        return "contactsdelete";
    }

    public String editt(Contact contact){
        jdbcTemplate.update("UPDATE contacts SET number=?, name=?, surname=? WHERE id=?",
                contact.getNumber(), contact.getName(), contact.getSurname(), contact.getId());

        return "contactsedit";
    }

}
