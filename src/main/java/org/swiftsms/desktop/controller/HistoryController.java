package org.swiftsms.desktop.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.desktop.dao.MessageDao;
import org.swiftsms.desktop.service.ConversationService;
import org.swiftsms.model.Contact;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HistoryController implements Initializable {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ConversationService conversationService;

    @FXML
    private ListView uiHistory;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final List<Contact> contacts = messageDao.getRecentContacts();
        uiHistory.setItems(FXCollections.observableArrayList(contacts));

        uiHistory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            conversationService.setRecipient(((Contact) newValue).getNumber());
        });

        uiHistory.getSelectionModel().select(0);
    }
}
