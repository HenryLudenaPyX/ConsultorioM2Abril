package com.prototype.cm2a.controllers.billing;

import com.prototype.cm2a.controllers.history.*;
import com.prototype.cm2a.controllers.medicine.MedicamentRegisterController;
import com.prototype.cm2a.controllers.medicine.MedicamentSearchController;
import com.prototype.cm2a.controllers.pacient.PacientRegisterController;
import com.prototype.cm2a.controllers.pacient.PacientSearchController;
import com.prototype.cm2a.controllers.parameter.ParameterRegisterController;
import com.prototype.cm2a.controllers.parameter.ParameterSearchController;
import com.prototype.cm2a.controllers.principalwindows.DashboardController;
import com.prototype.cm2a.controllers.user.UserRegisterController;
import com.prototype.cm2a.controllers.user.UserSearchController;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Message;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class BillingSearchController {

    @FXML
    private Button btnBackHome;

    @FXML
    private MenuItem btnGenerateBilling;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnViewMore;

    @FXML
    private Button btnCancel;

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
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private ImageView imgSearchIcon;

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
    private Label titleModule;

    @FXML
    private TextField txtFSearch;

    private String userRolAutentication;

    User userAutenticator = new User();

    Message message = new Message();

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

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
            System.out.println("Error in logOut - BillingGenerateController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DashboardController dashboardController = loader.getController();
            dashboardController.setDataAutentication(userAutenticator);
            Stage currentStage = (Stage) btnBackHome.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openDashboardHome - BillingGenerateController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openGenerateBilling(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administración") || userRolAutentication.equalsIgnoreCase("Administrador")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/billing/billing-generate.fxml"));
                Parent root = loader.load();
                BillingGenerateController billingGenerateController = loader.getController();
                billingGenerateController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openGenerateBilling : " + e.getMessage());
                e.printStackTrace();
            }
        }else{
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openQueryBilling(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administración") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/billing/billing-search.fxml"));
                Parent root = loader.load();
                BillingSearchController billingSearchController = loader.getController();
                billingSearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openQueryBilling : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openQueryClinicHistory(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/clinic-history-search.fxml"));
                Parent root = loader.load();
                ClinicHistorySearchController clinicHistorySearchController = loader.getController();
                clinicHistorySearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openQueryClinicHistory : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openQueryMedicament(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/medicine/medicament-search.fxml"));
            Parent root = loader.load();
            MedicamentSearchController medicamentSearchController = loader.getController();
            medicamentSearchController.setDataAutentication(userAutenticator);
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) menuModules.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openQueryMedicament : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openQueryPacient(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/pacient/pacient-search.fxml"));
                Parent root = loader.load();
                PacientSearchController pacientSearchController = loader.getController();
                pacientSearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openQueryPacient : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openQueryParameter(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/parameter/parameter-search.fxml"));
                Parent root = loader.load();
                ParameterSearchController parameterSearchController = loader.getController();
                parameterSearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Error in openQueryParameter : " + e.getMessage());
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openQueryUser(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/user/user-search.fxml"));
                Parent root = loader.load();
                UserSearchController userSearchController = loader.getController();
                userSearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Error in openQueryUser : " + e.getMessage());
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterClinicHistory(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/clinic-history-register.fxml"));
                Parent root = loader.load();
                ClinicHistoryRegisterController clinicHistoryRegisterController = loader.getController();
                clinicHistoryRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterClinicHistory : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterMedicalAttention(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/medical-attention-register.fxml"));
                Parent root = loader.load();
                MedicalAttentionRegisterController medicalAttentionRegisterController = loader.getController();
                medicalAttentionRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterMedicament(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administración") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/medicine/medicament-register.fxml"));
                Parent root = loader.load();
                MedicamentRegisterController medicamentRegisterController = loader.getController();
                medicamentRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterMedicament : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterPacient(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/pacient/pacient-register.fxml"));
                Parent root = loader.load();
                PacientRegisterController pacientRegisterController = loader.getController();
                pacientRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Error in openRegisterPacient : " + e.getMessage());
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterParameter(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/parameter/parameter-register.fxml"));
                Parent root = loader.load();
                ParameterRegisterController parameterRegisterController = loader.getController();
                parameterRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterParameter : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterTypeMedicalAttention(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador") || userRolAutentication.equalsIgnoreCase("Administración")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/type-medical-attention-register.fxml"));
                Parent root = loader.load();
                TypeMedicalAttentionRegisterController typeMedicalAttentionRegisterController = loader.getController();
                typeMedicalAttentionRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterTypeMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterUser(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/user/user-register.fxml"));
                Parent root = loader.load();
                UserRegisterController userRegisterController = loader.getController();
                userRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openRegisterUser : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openSearchTypeMedicalAttention(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administrador") || userRolAutentication.equalsIgnoreCase("Administración")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/type-medical-attention-search.fxml"));
                Parent root = loader.load();
                TypeMedicalAttentionSearchController typeMedicalAttentionSearchController = loader.getController();
                typeMedicalAttentionSearchController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in openSearchTypeMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openViewMore(ActionEvent event) {

    }

    @FXML
    void unsubscribe(ActionEvent event) {

    }
}
