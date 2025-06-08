package com.prototype.cm2a.components;

import com.prototype.cm2a.models.Medicament;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StockNotification {

    @FXML
    private Pane card;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStock;

    private Medicament medicament;

    public void setDataStockNotification(Medicament medicament) {
        this.medicament = medicament;
        lblCode.setText(String.valueOf(medicament.getId()));
        lblName.setText(medicament.getName());
        lblStock.setText(String.valueOf(medicament.getStock()));
    }
}
