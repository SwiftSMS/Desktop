package org.swiftsms.desktop.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.model.Message;

import java.util.List;

@Component
public class MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> getHistory() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query getAllQuery = session.createQuery("SELECT DISTINCT message.recipients FROM Message message"); // where number = ?
        final List<Message> value = getAllQuery.list();

        session.getTransaction().commit();
        session.close();

        return value;
    }

    public List<Message> getConversation(final String number) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query getAllQuery = session.createQuery("from Message"); // where number = ?
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
