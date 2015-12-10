package com.swift.java.dao;

import org.swiftsms.model.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Account> get() {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query query = session.createQuery("from Account");
        final List<Account> accounts = query.list();

        session.getTransaction().commit();
        session.close();

        return accounts;
    }

    public Account get(final int accountId) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Query query = session.createQuery("from Account where id = ?");
        query.setInteger(0, accountId);
        final Account account = (Account) query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return account;
    }

    public int insert(final Account account) {
        final Session session = sessionFactory.openSession();
        session.beginTransaction();

        final Integer id = (Integer) session.save(account);

        session.getTransaction().commit();
        session.close();

        return id;
    }
}
