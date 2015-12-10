package org.swiftsms.desktop.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private ListView uiHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiHistory.setItems(FXCollections.observableArrayList(
                "One",
                "Two",
                "Three",
                "Four",
                "Five"));
    }
}
