package com.prototype.cm2a.components;

import com.prototype.cm2a.controllers.pacient.PacientDetailsController;
import com.prototype.cm2a.controllers.pacient.PacientUpdateController;
import com.prototype.cm2a.models.Pacient;
import com.prototype.cm2a.models.User;
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
import java.sql.SQLException;

public class PacientCard {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnViewMore;

    @FXML
    private Pane card;

    @FXML
    private Label lblCI;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblNationality;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblSurnames;

    private Pacient pacient;

    public void setDataCard(Pacient pacient) {
        this.pacient = pacient;
        lblCI.setText(pacient.getIdNumber());
        lblNames.setText(pacient.getNames());
        lblSurnames.setText(pacient.getPaternalLastName() + " " + pacient.getMaternalLastName());
        lblNationality.setText(pacient.getNationality());
        lblPhone.setText(pacient.getPhoneNumber());
    }

    private String userRolAutentication;

    User userAutenticator = new User();

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
    }

    @FXML
    void openEdit(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/pacient/pacient-update.fxml"));
        Parent root = loader.load();
        PacientUpdateController pacientUpdateController = loader.getController();
        pacientUpdateController.setDetails(pacient.getIdNumber());
        pacientUpdateController.setDataAutentication(userAutenticator);
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) btnEdit.getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.show();
    }

    @FXML
    void openViewMore(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/pacient/pacient-details.fxml"));
        Parent root = loader.load();
        PacientDetailsController pacientDetailsController = loader.getController();
        pacientDetailsController.setDetails(pacient.getIdNumber());
        pacientDetailsController.setDataAutentication(userAutenticator);
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) btnViewMore.getScene().getWindow();
        currentStage.setScene(scene);
        currentStage.show();
    }

}
