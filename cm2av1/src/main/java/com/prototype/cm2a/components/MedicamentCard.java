package com.prototype.cm2a.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MedicamentCard {

    @FXML
    private Button btnUnsubscribe;

    @FXML
    private Button btnUpdateMedicamentDetails;

    @FXML
    private Button btnViewMore;

    @FXML
    private Pane card;

    @FXML
    private Label lblAditionalInformation;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblExpirationDate;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStock;

    @FXML
    void actionUnsubscribe(ActionEvent event) {

    }

    @FXML
    void openUpdateMedicamentDetails(ActionEvent event) {

    }

    @FXML
    void openViewMore(ActionEvent event) {

    }
}
