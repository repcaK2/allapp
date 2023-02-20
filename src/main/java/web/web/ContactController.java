package web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contacts")
    public String getAll(Model model){
        List<Contact> contacts = contactRepository.getAll();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/contactsform")
    public String contactsform(){
        return "contactsform";
    }

    @RequestMapping("/contactsanswer")
    public String getById(@RequestParam("id") int id, Model model){
        Contact contact = contactRepository.getById(id);
            model.addAttribute("contact", contact);
            return "contactsanswer";
    }

    @GetMapping("/add")
    public String adding(){
        return "adding";
    }

    @PostMapping("/contactsadd")
    public String contactsadd(@RequestParam("number")int number,
                              @RequestParam("name")String name,
                              @RequestParam("surname")String surname
                              ){
        Contact contact = new Contact();
        contact.setNumber(number);
        contact.setName(name);
        contact.setSurname(surname);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);

        return contactRepository.save(contacts);
    }

    @GetMapping("/delete")
    public String delete(){
        return "deleting";
    }

    @PostMapping("/contactsdelete")
    public String contactsdelete(@RequestParam("id")int id){
        return contactRepository.delete(id);
    }

    @GetMapping("/edit")
    public String edytor(){
        return "edit";
    }

    @PostMapping("/contactsedit")
    public String contactsedit(@RequestParam("id") int id,
                               @RequestParam("number")int number,
                               @RequestParam("name")String name,
                               @RequestParam("surname")String surname){
        Contact contact = new Contact();
        contact.setId(id);
        contact.setNumber(number);
        contact.setName(name);
        contact.setSurname(surname);

        return contactRepository.editt(contact);
    }



}

























