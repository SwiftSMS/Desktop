package com.swift.java.controller;

import com.swift.io.net.Operator;
import com.swift.io.net.OperatorFactory;
import com.swift.java.service.AccountService;
import com.swift.model.Account;
import com.swift.model.Message;
import com.swift.tasks.results.OperationResult;
import com.swift.utils.ContactUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComposeController {

    @Autowired
    private AccountService accountService;

    @FXML
    private TextField uiRecipients;

    @FXML
    private TextArea uiMessage;

    public void sendMessage(final ActionEvent actionEvent) {
        final Operator operator = accountService.getActiveAccount();

        final List<String> contacts = ContactUtils.getContactsAsList(uiRecipients.getText());
        final Message message = new Message(contacts, uiMessage.getText());

        System.out.printf("Sending message = %s%n", message);
        final OperationResult status = operator.send(contacts, uiMessage.getText());
        System.out.printf("Message status: %s%n", status.getStatus());

        uiRecipients.clear();
        uiMessage.clear();
    }
}
