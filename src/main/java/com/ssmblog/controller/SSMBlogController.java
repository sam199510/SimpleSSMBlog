package com.ssmblog.controller;

import com.ssmblog.entity.Contacts;
import com.ssmblog.service.ContactsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class SSMBlogController {
    @Autowired
    private ContactsServiceI contactsServiceI;

    public void setContactsServiceI(ContactsServiceI contactsServiceI) {
        this.contactsServiceI = contactsServiceI;
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView indexPage(){
        ModelAndView mav = new ModelAndView("index");
        List<Contacts> contacts = this.contactsServiceI.findAllContacts();
        mav.addObject("contacts",contacts);
        return mav;
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public ModelAndView newContactPage(){
        ModelAndView mav = new ModelAndView("newContact");
        return mav;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String newContact(@RequestParam("dateOfb")String dob, Contacts contacts) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dob);
        contacts.setDob(date);
        this.contactsServiceI.newContact(contacts);
        return "redirect:index.html";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteContact(int id){
        this.contactsServiceI.deleteContact(id);
        return "redirect:index.html";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView updateContactPage(int id){
        ModelAndView mav = new ModelAndView("editContact");
        Contacts contacts = this.contactsServiceI.selectByPrimaryKey(id);
        mav.addObject("editContact", contacts);
        return mav;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateContact(@RequestParam("dateOfb")String dob, Contacts contacts) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dob);
        contacts.setDob(date);
        this.contactsServiceI.updateContactByPrimaryKey(contacts);
        return "redirect:index.html";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView findByContactName(String name){
        ModelAndView mav = new ModelAndView("index");
        String contactName = "%" + name + "%";
        List<Contacts> contacts = this.contactsServiceI.findByContactName(contactName);
        mav.addObject("contacts",contacts);
        return mav;
    }

    @RequestMapping(value = "findAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contacts> findAllContacts(){
        return this.contactsServiceI.findAllContacts();
    }
}
