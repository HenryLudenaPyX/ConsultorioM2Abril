package com.prototype.cm2a.components;

import com.prototype.cm2a.models.Medicament;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ExpirationNotification {

    @FXML
    private Pane card;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblName;

    private Medicament medicament;

    public void setDataExpirationNotification(Medicament medicament) {
        this.medicament = medicament;
        lblCode.setText(String.valueOf(medicament.getId()));
        lblName.setText(medicament.getName());
        lblDate.setText(medicament.getExpirationDate());
    }
}
