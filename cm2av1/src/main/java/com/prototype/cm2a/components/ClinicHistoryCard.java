package com.prototype.cm2a.components;

import com.prototype.cm2a.controllers.history.ClinicHistoryDetailsController;
import com.prototype.cm2a.models.ClinicHistory;
import com.prototype.cm2a.models.Pacient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClinicHistoryCard {

    @FXML
    private Button btnViewMore;

    @FXML
    private Pane card;

    @FXML
    private Label lblCI;

    @FXML
    private Label lblId;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblSex;

    @FXML
    private Label lblSurnames;

    ClinicHistory clinicHistory;

    public void setData(ClinicHistory clinicHistory){
        this.clinicHistory = clinicHistory;
        lblId.setText(clinicHistory.getCodeHistoryClinic());
        lblNames.setText(clinicHistory.getNames());
        lblSurnames.setText(clinicHistory.getSurnames());
        lblCI.setText(clinicHistory.getIdentityCard());
        lblSex.setText(clinicHistory.getSex());
    }

    @FXML
    void openViewMore(ActionEvent event) {

    }
}
