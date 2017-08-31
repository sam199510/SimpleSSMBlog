package com.ssmblog.service;

import com.ssmblog.entity.Contacts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsServiceI {

    List<Contacts> findAllContacts();

    Contacts selectByPrimaryKey(int id);

    void newContact(Contacts contacts);

    void deleteContact(int id);

    void updateContactByPrimaryKey(Contacts contacts);

    List<Contacts> findByContactName(String name);
}
