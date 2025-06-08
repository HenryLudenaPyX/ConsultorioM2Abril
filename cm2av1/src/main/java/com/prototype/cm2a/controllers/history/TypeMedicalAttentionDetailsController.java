package com.prototype.cm2a.controllers.history;

import com.prototype.cm2a.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TypeMedicalAttentionDetailsController {

    @FXML
    private Button btnBackHome;

    @FXML
    private Button btnCancel;

    @FXML
    private MenuItem btnGenerateBilling;

    @FXML
    private Button btnLogOut;

    @FXML
    private MenuItem btnQueryBilling;

    @FXML
    private MenuItem btnQueryClinicHistory;

    @FXML
    private MenuItem btnQueryMedicament;

    @FXML
    private MenuItem btnQueryPacient;

    @FXML
    private MenuItem btnQueryParameter;

    @FXML
    private MenuItem btnQueryUser;

    @FXML
    private MenuItem btnRegisterClinicHistory;

    @FXML
    private MenuItem btnRegisterMedicalAttention;

    @FXML
    private MenuItem btnRegisterMedicament;

    @FXML
    private MenuItem btnRegisterPacient;

    @FXML
    private MenuItem btnRegisterParameter;

    @FXML
    private MenuItem btnRegisterTypeMedicalAttention;

    @FXML
    private MenuItem btnRegisterUser;

    @FXML
    private Button btnSave;

    @FXML
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblAttCode;

    @FXML
    private Label lblAttInformation;

    @FXML
    private Label lblAttName;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblSurnames;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TextField txtAttCode;

    @FXML
    private TextField txtAttName;

    @FXML
    private TextField txtAtteInformation;

    @FXML
    private TextField txtIce;

    @FXML
    private HBox txtIceCode;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtTurism;

    private String userRolAutentication;

    User userAutenticator = new User();

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

    @FXML
    void cancelRegister(ActionEvent event) {}

    @FXML
    void logOut(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/log.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnLogOut.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in Log out : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/type-medical-attention-search.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnLogOut.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error openDashboardHome - TypeMedicalAttentionDetailsController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openGenerateBilling(ActionEvent event) {}

    @FXML
    void openQueryBilling(ActionEvent event) {}

    @FXML
    void openQueryClinicHistory(ActionEvent event) {}

    @FXML
    void openQueryMedicament(ActionEvent event) {}

    @FXML
    void openQueryPacient(ActionEvent event) {}

    @FXML
    void openQueryParameter(ActionEvent event) {}

    @FXML
    void openQueryUser(ActionEvent event) {}

    @FXML
    void openRegisterClinicHistory(ActionEvent event) {}

    @FXML
    void openRegisterMedicalAttention(ActionEvent event) {}

    @FXML
    void openRegisterMedicament(ActionEvent event) {}

    @FXML
    void openRegisterPacient(ActionEvent event) {}

    @FXML
    void openRegisterParameter(ActionEvent event) {}

    @FXML
    void openRegisterTypeMedicalAttention(ActionEvent event) {}

    @FXML
    void openRegisterUser(ActionEvent event) {}

    @FXML
    void openSearchTypeMedicalAttention(ActionEvent event) {}

    @FXML
    void saveRegister(ActionEvent event) {

    }

}
