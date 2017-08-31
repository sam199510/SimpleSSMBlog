package com.ssmblog.dao;

import com.ssmblog.entity.Contacts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);

    List<Contacts> findAllContacts();

    List<Contacts> findByContactName(String name);
}