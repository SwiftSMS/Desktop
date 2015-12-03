package com.swift.java.controller;

import com.swift.java.service.AccountService;
import com.swift.model.Account;
import com.swift.model.Network;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AccountController implements Initializable {

    @Autowired
    private AccountService accountService;

    @FXML
    private TextField uiUsername;

    @FXML
    private TextField uiPassword;

    @FXML
    private ChoiceBox uiOperator;

    private ObservableList<Network> networks = FXCollections.observableArrayList(
            Network.METEOR,
            Network.THREE,
            Network.O2,
            Network.TESCO);

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        uiOperator.setItems(networks);

        for (final Account account : accountService.getAccounts()) {
            System.out.println(account);
        }
    }

    public String getUsername() {
        return uiUsername.getText();
    }

    public String getPassword() {
        return uiPassword.getText();
    }

    public Network getOperator() {
        final Object choice = uiOperator.getValue();
        return choice != null ? (Network) choice : null;
    }

    public void saveAccount(final ActionEvent actionEvent) {
        final String username = getUsername();
        final Network network = getOperator();
        final String password = getPassword();
        if (username.isEmpty() || password.isEmpty() || network == null) {
            System.err.println("You must fill in your account details");
            return;
        }

        final int accountId = accountService.addAccount(network.name(), username, password, network);
        accountService.setActiveAccount(accountId);
    }

}
