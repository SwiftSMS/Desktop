package com.swift.java.service;

import com.swift.io.net.Operator;
import com.swift.io.net.OperatorFactory;
import com.swift.java.dao.AccountDao;
import com.swift.java.dao.SettingsDao;
import com.swift.model.Account;
import com.swift.model.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private SettingsDao settingsDao;

    public int addAccount(final String accountName, final String username, final String password, final Network network) {
        final Account account = new Account(username, accountName, password, network);

        return accountDao.insert(account);
    }

    public List<Account> getAccounts() {
        return accountDao.get();
    }

    public Operator getActiveAccount() {
        final int accountId = Integer.valueOf(settingsDao.get("activeAccount"));
        final Account account = accountDao.get(accountId);

        return OperatorFactory.getOperator(account);
    }

    public void setActiveAccount(final int accountId) {
        settingsDao.set("activeAccount", String.valueOf(accountId));
    }
}
