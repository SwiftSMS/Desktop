package org.swiftsms.desktop.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swiftsms.desktop.dao.MessageDao;
import org.swiftsms.model.Message;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HistoryController implements Initializable {

    @Autowired
    private MessageDao messageDao;

    @FXML
    private ListView uiHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final List<Message> messages = messageDao.getHistory();
        uiHistory.setItems(FXCollections.observableArrayList(messages));
    }
}
