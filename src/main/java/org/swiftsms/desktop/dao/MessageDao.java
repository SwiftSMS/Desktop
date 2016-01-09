package org.swiftsms.desktop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.model.Contact;
import org.swiftsms.model.Message;

import java.util.List;

@Component
public class MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Contact> getHistory() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query getAllQuery = session.createQuery("SELECT DISTINCT m.recipients FROM Message m"); // where number = ?
        final List<Contact> value = getAllQuery.list();

        session.getTransaction().commit();
        session.close();

        return value;
    }

    public List<Message> getConversation(final Contact recipient) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query getAllQuery = session.createQuery("SELECT m FROM Message m INNER JOIN m.recipients r WHERE r.number IN (?)"); // where number = ?
        getAllQuery.setString(0, recipient.getNumber());
        final List<Message> value = getAllQuery.list();

        session.getTransaction().commit();
        session.close();

        return value;
    }

    public void save(final Message sentMessage) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(sentMessage);

        session.getTransaction().commit();
        session.close();
    }
}
