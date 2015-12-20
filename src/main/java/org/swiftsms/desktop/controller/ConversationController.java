package org.swiftsms.desktop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.desktop.service.ConversationService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@Component
public class ConversationController implements Initializable {

    @Autowired
    private ConversationService conversationService;

    @FXML
    private TextField uiRecipients;

    @FXML
    private ListView uiConversation;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        uiConversation.setItems(FXCollections.observableArrayList(
                "One",
                "Two",
                "Three"
        ));
    }

    public void updateRecipient(final Event event) {
        conversationService.setRecipient(uiRecipients.getText());
    }
}
