package org.swiftsms.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.desktop.dao.MessageDao;
import org.swiftsms.desktop.service.AccountService;
import org.swiftsms.desktop.service.ConversationService;
import org.swiftsms.io.net.Operator;
import org.swiftsms.model.Contact;
import org.swiftsms.model.Message;
import org.swiftsms.tasks.Status;
import org.swiftsms.tasks.results.OperationResult;
import org.swiftsms.utils.ContactUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ComposeController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageDao messageDao;

    @FXML
    private TextArea uiMessage;

    public void sendMessage(final ActionEvent actionEvent) {
        final Operator operator = accountService.getActiveAccount();

        final List<String> contacts = ContactUtils.getContactsAsList(conversationService.getRecipientNumber());
        final Message message = new Message(convertToContacts(contacts), uiMessage.getText());

        System.out.printf("Sending message = %s%n", message);
        final OperationResult status = operator.send(contacts, uiMessage.getText());
        System.out.printf("Message status: %s%n", status.getStatus());

        if (status.getStatus() == Status.SUCCESS) {
            uiMessage.clear();
            messageDao.save(message);
        }
    }

    private Set<Contact> convertToContacts(final List<String> contacts) {
        final Set<Contact> destination = new HashSet<>();
        for (final String number : contacts) {
            destination.add(new Contact(number, "First", "Last"));
        }
        return destination;
    }
}
