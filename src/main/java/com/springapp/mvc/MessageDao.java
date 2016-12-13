package com.springapp.mvc;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;



@Repository
@SuppressWarnings("unchecked")
public class MessageDao {
    @Autowired
    private Logger logger;


    @Autowired
    private SessionFactory sessionFactory;
    public List<String> getMessages(){
        logger.debug("***********************REQUEST PROCESSING***************************");
        Session session = sessionFactory.getCurrentSession();
        List<MessageEntity> list = session.
                createCriteria(MessageEntity.class).list();
        return list.stream().map(MessageEntity::getMessage).collect(Collectors.toList());
    }
}
