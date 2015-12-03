package com.swift.java.service;

import com.swift.io.net.Operator;
import com.swift.java.dao.AccountDao;
import com.swift.java.dao.SettingsDao;
import com.swift.model.Account;
import com.swift.model.Network;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountDao mAccountDao;

    @Mock
    private SettingsDao mSettingsDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAccount() throws Exception {
        when(mAccountDao.insert(any(Account.class))).thenReturn(1);

        final int accountId = accountService.addAccount("testAccount", "testUser", "testPassword", Network.THREE);

        assertEquals(1, accountId);
    }

    @Test
    public void getAccounts() throws Exception {
        final List<Account> accounts = new ArrayList<>();
        accounts.add(mock(Account.class));
        accounts.add(mock(Account.class));
        when(mAccountDao.get()).thenReturn(accounts);

        final List<Account> actual = accountService.getAccounts();

        assertEquals(accounts, actual);
        assertEquals(2, actual.size());
    }

    @Test
    public void getActiveAccount() {
        final String accName = "accName";
        final String accNumber = "0851234567";
        final String password = "password";
        final Network network = Network.EMOBILE;

        final Account account = new Account(accNumber, accName, password, network);
        when(mSettingsDao.get(anyString())).thenReturn("1");
        when(mAccountDao.get(1)).thenReturn(account);

        final Operator actual = accountService.getActiveAccount();

        assertEquals(account, actual.getAccount());
        assertEquals(accName, actual.getAccount().getAccountName());
        assertEquals(accNumber, actual.getAccount().getMobileNumber());
        assertEquals(password, actual.getAccount().getPassword());
        assertEquals(network, actual.getAccount().getOperator());
    }

    @Test
    public void setActiveAccount() {
        final int accId = 123;

        accountService.setActiveAccount(accId);

        verify(mSettingsDao).set("activeAccount", Integer.toString(accId));
    }
}