package com.swift.java.dao;

import com.swift.java.model.Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettingsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public String get(final String key) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Settings value = session.get(Settings.class, key);

        session.getTransaction().commit();
        session.close();

        return value.getValue();
    }

    public void set(final String key, final String value) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Settings settings = new Settings();
        settings.setKey(key);
        settings.setValue(value);

        session.saveOrUpdate(settings);

        session.getTransaction().commit();
        session.close();
    }
}
