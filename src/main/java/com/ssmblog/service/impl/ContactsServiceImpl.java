package com.ssmblog.service.impl;

import com.ssmblog.dao.ContactsMapper;
import com.ssmblog.entity.Contacts;
import com.ssmblog.service.ContactsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contactsService")
@Transactional
public class ContactsServiceImpl implements ContactsServiceI{

    @Autowired
    private ContactsMapper contactsMapper;

    public void setContactsMapper(ContactsMapper contactsMapper) {
        this.contactsMapper = contactsMapper;
    }

    @Override
    public List<Contacts> findAllContacts() {
        return this.contactsMapper.findAllContacts();
    }

    @Override
    public Contacts selectByPrimaryKey(int id) {
        return this.contactsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void newContact(Contacts contacts) {
        this.contactsMapper.insert(contacts);
    }

    @Override
    public void deleteContact(int id) {
        this.contactsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateContactByPrimaryKey(Contacts contacts) {
        this.contactsMapper.updateByPrimaryKey(contacts);
    }

    @Override
    public List<Contacts> findByContactName(String name) {
        return this.contactsMapper.findByContactName(name);
    }
}
