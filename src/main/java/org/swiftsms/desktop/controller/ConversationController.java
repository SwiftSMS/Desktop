package org.swiftsms.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.desktop.dao.MessageDao;
import org.swiftsms.desktop.service.ConversationService;
import org.swiftsms.model.Message;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ConversationController implements Initializable {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageDao messageDao;

    @FXML
    private TextField uiRecipients;

    @FXML
    private ListView uiConversation;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final List<Message> messages = messageDao.getConversation(conversationService.getRecipient());

        uiConversation.setItems(FXCollections.observableArrayList(messages));
    }

    public void updateRecipient(final Event event) {
        conversationService.setRecipient(uiRecipients.getText());
    }
}

