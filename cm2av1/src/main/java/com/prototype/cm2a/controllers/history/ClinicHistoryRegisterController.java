package com.prototype.cm2a.controllers.history;

import com.prototype.cm2a.controllers.billing.BillingGenerateController;
import com.prototype.cm2a.controllers.billing.BillingSearchController;
import com.prototype.cm2a.controllers.medicine.MedicamentRegisterController;
import com.prototype.cm2a.controllers.medicine.MedicamentSearchController;
import com.prototype.cm2a.controllers.pacient.PacientRegisterController;
import com.prototype.cm2a.controllers.pacient.PacientSearchController;
import com.prototype.cm2a.controllers.parameter.ParameterRegisterController;
import com.prototype.cm2a.controllers.parameter.ParameterSearchController;
import com.prototype.cm2a.controllers.principalwindows.DashboardController;
import com.prototype.cm2a.controllers.user.UserRegisterController;
import com.prototype.cm2a.controllers.user.UserSearchController;
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Functions;
import com.prototype.cm2a.utils.Message;
import com.prototype.cm2a.utils.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClinicHistoryRegisterController {

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
    private MenuItem btnSearchTypeMedicalAttention1;

    @FXML
    private DatePicker dateVitalSigns;

    @FXML
    private DatePicker dateVitalSigns1;

    @FXML
    private DatePicker dateVitalSigns2;

    @FXML
    private DatePicker dateVitalSigns3;

    @FXML
    private DatePicker dateVitalSigns4;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblCardNumber;

    @FXML
    private Label lblClinicHistory;

    @FXML
    private Label lblConsultReason;

    @FXML
    private Label lblCurrentRevision;

    @FXML
    private Label lblDateControl;

    @FXML
    private Label lblDisease;

    @FXML
    private Label lblFamilyAntecedents;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblHourControl;

    @FXML
    private Label lblNameDoctor;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblNumberSheet;

    @FXML
    private Label lblOptional;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPersonalAntecedents;

    @FXML
    private Label lblPhysycExam;

    @FXML
    private Label lblPlans;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblSurnames;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TextField pressureVitalSigns;

    @FXML
    private TextField pressureVitalSigns1;

    @FXML
    private TextField pressureVitalSigns2;

    @FXML
    private TextField pressureVitalSigns3;

    @FXML
    private TextField pressureVitalSigns4;

    @FXML
    private TextField pulseVitalSigns;

    @FXML
    private TextField pulseVitalSigns1;

    @FXML
    private TextField pulseVitalSigns2;

    @FXML
    private TextField pulseVitalSigns3;

    @FXML
    private TextField pulseVitalSigns4;

    @FXML
    private TextField temperatureVitalSigns;

    @FXML
    private TextField temperatureVitalSigns1;

    @FXML
    private TextField temperatureVitalSigns2;

    @FXML
    private TextField temperatureVitalSigns3;

    @FXML
    private TextField temperatureVitalSigns4;

    @FXML
    private Label titleModule;

    @FXML
    private Label titleModule1;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private TextField txtCodeCie;

    @FXML
    private TextField txtCodeCie1;

    @FXML
    private TextField txtCodeCie2;

    @FXML
    private TextField txtCodeCie3;

    @FXML
    private TextField txtCodeClinicHistory;

    @FXML
    private TextArea txtCurentRevision;

    @FXML
    private DatePicker txtDateControl;

    @FXML
    private TextField txtDiagnosticName;

    @FXML
    private TextField txtDiagnosticName1;

    @FXML
    private TextField txtDiagnosticName2;

    @FXML
    private TextField txtDiagnosticName3;

    @FXML
    private TextArea txtDisease;

    @FXML
    private TextField txtEstablishment;

    @FXML
    private TextArea txtFamiliarAntecedents;

    @FXML
    private TextField txtHourControl;

    @FXML
    private TextField txtLastnamesPacient;

    @FXML
    private TextField txtNameDoctor;

    @FXML
    private TextField txtNamesPacient;

    @FXML
    private TextField txtNumberSheet;

    @FXML
    private ComboBox<String> txtOpcionPreDef;
    private String[] items = {"PRE", "DEF"};

    @FXML
    private ComboBox<String> txtOpcionPreDef1;

    @FXML
    private ComboBox<String> txtOpcionPreDef2;

    @FXML
    private ComboBox<String> txtOpcionPreDef3;

    @FXML
    private TextArea txtPersonalAntecedents;

    @FXML
    private TextArea txtPhysycExam;

    @FXML
    private TextArea txtPlans;

    @FXML
    private TextArea txtReasonConsultation;

    private String userRolAutentication;

    User userAutenticator = new User();

    Message message = new Message();

    List<String> errors = new ArrayList<>();

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

    @FXML
    public void initialize() {
        setData();
        dateVitalSigns.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        dateVitalSigns1.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        dateVitalSigns2.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        dateVitalSigns3.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        dateVitalSigns4.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        txtDateControl.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.trim().isEmpty()) {
                    try {
                        return LocalDate.parse(string, dateFormatter);
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        txtOpcionPreDef.getItems().addAll(items);
        txtOpcionPreDef1.getItems().addAll(items);
        txtOpcionPreDef2.getItems().addAll(items);
        txtOpcionPreDef3.getItems().addAll(items);
        Functions.updateDateTime(lblHour);
        txtCodeClinicHistory.setOnAction(e -> {
            Validations.validateExistenceCodeClinic(txtCodeClinicHistory, lblClinicHistory, errors, "clinic_history");
            txtNumberSheet.requestFocus();
        });
        txtNumberSheet.setOnAction(e -> {
            Validations.validateNumberClinic(txtNumberSheet, lblNumberSheet, errors);
            txtCardNumber.requestFocus();
        });
        txtCardNumber.setOnAction(e -> {
            Validations.validateNoExistenceIdentityCard(lblCardNumber, txtCardNumber, errors, "pacient");
            setDataExistence(txtCardNumber.getText());
            txtReasonConsultation.requestFocus();
        });
        /*
        txtReasonConsultation.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Validations.validateNullsV2(txtReasonConsultation, lblConsultReason, errors);
                txtPersonalAntecedents.requestFocus();
                event.consume();
            }
        });
        txtPersonalAntecedents.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Validations.validateNullsV2(txtPersonalAntecedents, lblPersonalAntecedents, errors);
                txtFamiliarAntecedents.requestFocus();
                event.consume();
            }
        });
        txtFamiliarAntecedents.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Validations.validateNullsV2(txtFamiliarAntecedents, lblPersonalAntecedents, errors);
                txtDisease.requestFocus();
                event.consume();
            }
        });
        txtDisease.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Validations.validateNullsV2(txtDisease, lblDisease, errors);
                txtCurentRevision.requestFocus();
                event.consume();
            }
        });
        txtCurentRevision.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Validations.validateNullsV2(txtCurentRevision, lblCurrentRevision, errors);
                dateVitalSigns.requestFocus();
                event.consume();
            }
        });
         */
    }

    @FXML
    void cancelRegistr(ActionEvent event) {
        resetStyle();
        resetTextFields();
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
    void saveRegister(ActionEvent event) {
        errors = validateInputs();
        List<String> errorInputs = new ArrayList<>();
        String insertClinicHistory = "INSERT INTO clinic_history (id, numberSheet, idNumber, consultReason, personalAntecedents, familyAntecedents, disease, currentRevision, physicExam, plansDiagnostic, controlDate, controlHour, nameMedic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertVitalsSigns = "INSERT INTO vital_signs (line, id_clinic_history, numberSheet, date_attention, pressure, pulse, temperature) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertDiagnostic = "INSERT INTO diagnostic (line, id_clinic_history, numberSheet, description, codeCie, type_diagnostic) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (errors.isEmpty()) {
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatementClicinHistory = connection.prepareStatement(insertClinicHistory);
                    preparedStatementClicinHistory.setString(1, txtCodeClinicHistory.getText());
                    preparedStatementClicinHistory.setInt(2, Integer.parseInt(txtNumberSheet.getText()));
                    preparedStatementClicinHistory.setString(3, txtCardNumber.getText());
                    preparedStatementClicinHistory.setString(4, txtReasonConsultation.getText());
                    preparedStatementClicinHistory.setString(5, txtPersonalAntecedents.getText());
                    preparedStatementClicinHistory.setString(6, txtFamiliarAntecedents.getText());
                    preparedStatementClicinHistory.setString(7, txtDisease.getText());
                    preparedStatementClicinHistory.setString(8, txtCurentRevision.getText());
                    preparedStatementClicinHistory.setString(9, txtPhysycExam.getText());
                    preparedStatementClicinHistory.setString(10, txtPlans.getText());
                    preparedStatementClicinHistory.setDate(11, Date.valueOf(txtDateControl.getValue()));
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime localAttentionTime = LocalTime.parse(txtHourControl.getText(), timeFormatter);
                    preparedStatementClicinHistory.setTime(12, Time.valueOf(localAttentionTime));
                    preparedStatementClicinHistory.setString(13, txtNameDoctor.getText());
                    preparedStatementClicinHistory.addBatch();
                    PreparedStatement preparedStatementVitalsSigns = connection.prepareStatement(insertVitalsSigns);
                    PreparedStatement preparedStatementDiagnostic = connection.prepareStatement(insertDiagnostic);
                    int cLine = 1;
                    if(!(dateVitalSigns.getValue() == null) && !pressureVitalSigns.getText().isEmpty() && !pulseVitalSigns.getText().isEmpty() && !temperatureVitalSigns.getText().isEmpty()){
                        if (Validations.checkDateVS(dateVitalSigns, lblOptional) && Validations.checkPressure(pressureVitalSigns, lblOptional) && Validations.checkPulse(pulseVitalSigns, lblOptional) && Validations.checkTemperature(temperatureVitalSigns, lblOptional)){
                            preparedStatementVitalsSigns.setInt(1, cLine);
                            preparedStatementVitalsSigns.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementVitalsSigns.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementVitalsSigns.setDate(4, Date.valueOf(dateVitalSigns.getValue()));
                            preparedStatementVitalsSigns.setString(5, pressureVitalSigns.getText());
                            preparedStatementVitalsSigns.setInt(6, Integer.parseInt(pulseVitalSigns.getText()));
                            preparedStatementVitalsSigns.setString(7, temperatureVitalSigns.getText());
                            cLine++;
                            preparedStatementVitalsSigns.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else{
                        dateVitalSigns.getEditor().clear();
                        pressureVitalSigns.clear();
                        pulseVitalSigns.clear();
                        temperatureVitalSigns.clear();
                    }
                    if(!(dateVitalSigns1.getValue() == null) && !pressureVitalSigns1.getText().isEmpty() && !pulseVitalSigns1.getText().isEmpty() && !temperatureVitalSigns1.getText().isEmpty()){
                        if (Validations.checkDateVS(dateVitalSigns1, lblOptional) && Validations.checkPressure(pressureVitalSigns1, lblOptional) && Validations.checkPulse(pulseVitalSigns1, lblOptional) && Validations.checkTemperature(temperatureVitalSigns1, lblOptional)){
                            preparedStatementVitalsSigns.setInt(1, cLine);
                            preparedStatementVitalsSigns.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementVitalsSigns.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementVitalsSigns.setDate(4, Date.valueOf(dateVitalSigns1.getValue()));
                            preparedStatementVitalsSigns.setString(5, pressureVitalSigns1.getText());
                            preparedStatementVitalsSigns.setInt(6, Integer.parseInt(pulseVitalSigns1.getText()));
                            preparedStatementVitalsSigns.setString(7, temperatureVitalSigns1.getText());
                            cLine++;
                            preparedStatementVitalsSigns.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else{
                        dateVitalSigns1.getEditor().clear();
                        pressureVitalSigns1.clear();
                        pulseVitalSigns1.clear();
                        temperatureVitalSigns1.clear();
                    }
                    if(!(dateVitalSigns2.getValue() == null) && !pressureVitalSigns2.getText().isEmpty() && !pulseVitalSigns2.getText().isEmpty() && !temperatureVitalSigns2.getText().isEmpty()){
                        if (Validations.checkDateVS(dateVitalSigns2, lblOptional) && Validations.checkPressure(pressureVitalSigns2, lblOptional) && Validations.checkPulse(pulseVitalSigns2, lblOptional) && Validations.checkTemperature(temperatureVitalSigns2, lblOptional)){
                            preparedStatementVitalsSigns.setInt(1, cLine);
                            preparedStatementVitalsSigns.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementVitalsSigns.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementVitalsSigns.setDate(4, Date.valueOf(dateVitalSigns2.getValue()));
                            preparedStatementVitalsSigns.setString(5, pressureVitalSigns2.getText());
                            preparedStatementVitalsSigns.setInt(6, Integer.parseInt(pulseVitalSigns2.getText()));
                            preparedStatementVitalsSigns.setString(7, temperatureVitalSigns2.getText());
                            cLine++;
                            preparedStatementVitalsSigns.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else{
                        dateVitalSigns2.getEditor().clear();
                        pressureVitalSigns2.clear();
                        pulseVitalSigns2.clear();
                        temperatureVitalSigns2.clear();
                    }
                    if(!(dateVitalSigns3.getValue() == null) && !pressureVitalSigns3.getText().isEmpty() && !pulseVitalSigns3.getText().isEmpty() && !temperatureVitalSigns3.getText().isEmpty()){
                        if (Validations.checkDateVS(dateVitalSigns3, lblOptional) && Validations.checkPressure(pressureVitalSigns3, lblOptional) && Validations.checkPulse(pulseVitalSigns3, lblOptional) && Validations.checkTemperature(temperatureVitalSigns3, lblOptional)){
                            preparedStatementVitalsSigns.setInt(1, cLine);
                            preparedStatementVitalsSigns.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementVitalsSigns.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementVitalsSigns.setDate(4, Date.valueOf(dateVitalSigns3.getValue()));
                            preparedStatementVitalsSigns.setString(5, pressureVitalSigns3.getText());
                            preparedStatementVitalsSigns.setInt(6, Integer.parseInt(pulseVitalSigns3.getText()));
                            preparedStatementVitalsSigns.setString(7, temperatureVitalSigns3.getText());
                            cLine++;
                            preparedStatementVitalsSigns.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        dateVitalSigns3.getEditor().clear();
                        pressureVitalSigns3.clear();
                        pulseVitalSigns3.clear();
                        temperatureVitalSigns3.clear();
                    }
                    if(!(dateVitalSigns4.getValue() == null) && !pressureVitalSigns4.getText().isEmpty() && !pulseVitalSigns4.getText().isEmpty() && !temperatureVitalSigns4.getText().isEmpty()){
                        if (Validations.checkDateVS(dateVitalSigns4, lblOptional) && Validations.checkPressure(pressureVitalSigns4, lblOptional) && Validations.checkPulse(pulseVitalSigns4, lblOptional) && Validations.checkTemperature(temperatureVitalSigns4, lblOptional)){
                            preparedStatementVitalsSigns.setInt(1, cLine);
                            preparedStatementVitalsSigns.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementVitalsSigns.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementVitalsSigns.setDate(4, Date.valueOf(dateVitalSigns4.getValue()));
                            preparedStatementVitalsSigns.setString(5, pressureVitalSigns4.getText());
                            preparedStatementVitalsSigns.setInt(6, Integer.parseInt(pulseVitalSigns4.getText()));
                            preparedStatementVitalsSigns.setString(7, temperatureVitalSigns4.getText());
                            preparedStatementVitalsSigns.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        dateVitalSigns4.getEditor().clear();
                        pressureVitalSigns4.clear();
                        pulseVitalSigns4.clear();
                        temperatureVitalSigns4.clear();
                    }
                    int currentLine = 1;
                    if(!txtDiagnosticName.getText().isEmpty() && !txtCodeCie.getText().isEmpty() && !txtOpcionPreDef.getValue().isEmpty()){
                        if(Validations.checkDescription(txtDiagnosticName, lblOptional) && Validations.checkCIE(txtCodeCie, lblOptional) && Validations.checkPreDef(txtOpcionPreDef, lblOptional)) {
                            preparedStatementDiagnostic.setInt(1, currentLine);
                            preparedStatementDiagnostic.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementDiagnostic.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementDiagnostic.setString(4, txtDiagnosticName.getText());
                            preparedStatementDiagnostic.setString(5, txtCodeCie.getText());
                            preparedStatementDiagnostic.setString(6, txtOpcionPreDef.getValue());
                            currentLine++;
                            preparedStatementDiagnostic.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        txtDiagnosticName.clear();
                        txtCodeCie.clear();
                        txtOpcionPreDef.getSelectionModel().clearSelection();
                    }
                    if(!txtDiagnosticName1.getText().isEmpty() && !txtCodeCie1.getText().isEmpty() && !txtOpcionPreDef1.getValue().isEmpty()){
                        if(Validations.checkDescription(txtDiagnosticName1, lblOptional) && Validations.checkCIE(txtCodeCie1, lblOptional) && Validations.checkPreDef(txtOpcionPreDef1, lblOptional)) {
                            preparedStatementDiagnostic.setInt(1, currentLine);
                            preparedStatementDiagnostic.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementDiagnostic.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementDiagnostic.setString(4, txtDiagnosticName1.getText());
                            preparedStatementDiagnostic.setString(5, txtCodeCie1.getText());
                            preparedStatementDiagnostic.setString(6, txtOpcionPreDef1.getValue());
                            currentLine++;
                            preparedStatementDiagnostic.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        txtDiagnosticName1.clear();
                        txtCodeCie1.clear();
                        txtOpcionPreDef1.getSelectionModel().clearSelection();
                    }
                    if(!txtDiagnosticName2.getText().isEmpty() && !txtCodeCie2.getText().isEmpty() && !txtOpcionPreDef2.getValue().isEmpty()){
                        if(Validations.checkDescription(txtDiagnosticName2, lblOptional) && Validations.checkCIE(txtCodeCie2, lblOptional) && Validations.checkPreDef(txtOpcionPreDef2, lblOptional)) {
                            preparedStatementDiagnostic.setInt(1, currentLine);
                            preparedStatementDiagnostic.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementDiagnostic.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementDiagnostic.setString(4, txtDiagnosticName2.getText());
                            preparedStatementDiagnostic.setString(5, txtCodeCie2.getText());
                            preparedStatementDiagnostic.setString(6, txtOpcionPreDef2.getValue());
                            currentLine++;
                            preparedStatementDiagnostic.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        txtDiagnosticName2.clear();
                        txtCodeCie2.clear();
                        txtOpcionPreDef2.getSelectionModel().clearSelection();
                    }
                    if(!txtDiagnosticName3.getText().isEmpty() && !txtCodeCie3.getText().isEmpty() && !txtOpcionPreDef3.getValue().isEmpty()){
                        if(Validations.checkDescription(txtDiagnosticName3, lblOptional) && Validations.checkCIE(txtCodeCie3, lblOptional) && Validations.checkPreDef(txtOpcionPreDef3, lblOptional)){
                            preparedStatementDiagnostic.setInt(1, currentLine);
                            preparedStatementDiagnostic.setString(2, txtCodeClinicHistory.getText());
                            preparedStatementDiagnostic.setInt(3, Integer.parseInt(txtNumberSheet.getText()));
                            preparedStatementDiagnostic.setString(4, txtDiagnosticName3.getText());
                            preparedStatementDiagnostic.setString(5, txtCodeCie3.getText());
                            preparedStatementDiagnostic.setString(6, txtOpcionPreDef3.getValue());
                            preparedStatementDiagnostic.addBatch();
                        }else {
                            errorInputs.add("");
                        }
                    }else {
                        txtDiagnosticName3.clear();
                        txtCodeCie3.clear();
                        txtOpcionPreDef3.getSelectionModel().clearSelection();
                    }
                    if(errorInputs.isEmpty()){
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Mensaje de confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("Estás seguro de guardar la historia clínica?");
                        Optional<ButtonType> op = alert.showAndWait();
                        if (op.get().equals(ButtonType.OK)) {
                            preparedStatementClicinHistory.executeBatch();
                            preparedStatementVitalsSigns.executeBatch();
                            preparedStatementDiagnostic.executeBatch();
                            connection.commit();
                            resetTextFields();
                            resetStyle();
                            alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Mensaje de confirmación");
                            alert.setHeaderText(null);
                            alert.setContentText("La Historia Clínica se ha guardado exitosamente");
                            alert.showAndWait();
                        }else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("!ERROR!");
                            alert.setHeaderText(null);
                            alert.setContentText("No se guardo la Historia Clínica");
                            alert.showAndWait();
                        }
                    }else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("!ERROR!");
                        alert.setHeaderText(null);
                        alert.setContentText("Llene los campos correctamente");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    try {
                        if (connection != null) {
                            connection.rollback();
                        }
                    } catch (SQLException rollbackEx) {
                        System.out.println("Error en rollback: " + rollbackEx.getMessage());
                        rollbackEx.printStackTrace();
                    }
                    System.out.println("Error en guardar Historia: " + e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.setAutoCommit(true);
                            connection.close();
                        }
                    } catch (SQLException closeEx) {
                        System.out.println("Error al cerrar conexión: " + closeEx.getMessage());
                        closeEx.printStackTrace();
                    }
                }
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("!ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Llene los campos correctamente");
            alert.showAndWait();
        }
    }

    public void setData () {
        String sql = "SELECT name FROM branch";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                txtEstablishment.setText(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error in setDataBill : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void resetTextFields(){
        txtCardNumber.clear();
        txtNumberSheet.clear();
        txtNamesPacient.clear();
        txtLastnamesPacient.clear();
        txtCardNumber.clear();
        txtCodeClinicHistory.clear();
        txtReasonConsultation.clear();
        txtPersonalAntecedents.clear();
        txtFamiliarAntecedents.clear();
        txtDisease.clear();
        txtCurentRevision.clear();
        dateVitalSigns.getEditor().clear();
        dateVitalSigns1.getEditor().clear();
        dateVitalSigns2.getEditor().clear();
        dateVitalSigns3.getEditor().clear();
        dateVitalSigns4.getEditor().clear();
        pressureVitalSigns.clear();
        pressureVitalSigns1.clear();
        pressureVitalSigns2.clear();
        pressureVitalSigns3.clear();
        pressureVitalSigns4.clear();
        pulseVitalSigns.clear();
        pulseVitalSigns1.clear();
        pulseVitalSigns2.clear();
        pulseVitalSigns3.clear();
        pulseVitalSigns4.clear();
        temperatureVitalSigns.clear();
        temperatureVitalSigns1.clear();
        temperatureVitalSigns2.clear();
        temperatureVitalSigns3.clear();
        temperatureVitalSigns4.clear();
        txtPhysycExam.clear();
        txtDiagnosticName.clear();
        txtDiagnosticName1.clear();
        txtDiagnosticName2.clear();
        txtDiagnosticName3.clear();
        txtCodeCie.clear();
        txtCodeCie1.clear();
        txtCodeCie2.clear();
        txtCodeCie3.clear();
        txtOpcionPreDef.getSelectionModel().clearSelection();
        txtOpcionPreDef1.getSelectionModel().clearSelection();
        txtOpcionPreDef2.getSelectionModel().clearSelection();
        txtOpcionPreDef3.getSelectionModel().clearSelection();
        txtPlans.clear();
        txtDateControl.getEditor().clear();
        txtHourControl.clear();
        txtNameDoctor.clear();
    }
    public void resetStyle(){
        Validations.restartStyles(txtCardNumber, lblCardNumber);
        Validations.restartStyles(txtNumberSheet, lblNumberSheet);
        Validations.restartStyles(txtCodeClinicHistory, lblClinicHistory);
        Validations.restartStylesV3(txtReasonConsultation, lblConsultReason);
        Validations.restartStylesV3(txtPersonalAntecedents, lblPersonalAntecedents);
        Validations.restartStylesV3(txtFamiliarAntecedents, lblFamilyAntecedents);
        Validations.restartStylesV3(txtDisease, lblDisease);
        Validations.restartStylesV3(txtCurentRevision, lblCurrentRevision);
        Validations.restartStylesV3(txtPhysycExam, lblPhysycExam);
        Validations.restartStylesV3(txtPlans, lblPlans);
        Validations.restartStyles(txtDiagnosticName, lblOptional);
        Validations.restartStyles(txtCodeCie, lblOptional);
        Validations.restartStylesV2(txtOpcionPreDef, lblOptional);
        Validations.restartStyles(txtDiagnosticName1, lblOptional);
        Validations.restartStyles(txtCodeCie1, lblOptional);
        Validations.restartStylesV2(txtOpcionPreDef1, lblOptional);
        Validations.restartStyles(txtDiagnosticName2, lblOptional);
        Validations.restartStyles(txtCodeCie2, lblOptional);
        Validations.restartStylesV2(txtOpcionPreDef2, lblOptional);
        Validations.restartStyles(txtDiagnosticName3, lblOptional);
        Validations.restartStyles(txtCodeCie3, lblOptional);
        Validations.restartStylesV2(txtOpcionPreDef3, lblOptional);
        Validations.restartStylesV4(txtDateControl, lblDateControl);
        Validations.restartStyles(txtHourControl, lblHourControl);
        Validations.restartStyles(txtNameDoctor, lblNameDoctor);
        Validations.restartStylesV4(dateVitalSigns, lblOptional);
        Validations.restartStyles(pressureVitalSigns, lblOptional);
        Validations.restartStyles(pulseVitalSigns, lblOptional);
        Validations.restartStyles(temperatureVitalSigns, lblOptional);
        Validations.restartStylesV4(dateVitalSigns1, lblOptional);
        Validations.restartStyles(pressureVitalSigns1, lblOptional);
        Validations.restartStyles(pulseVitalSigns1, lblOptional);
        Validations.restartStyles(temperatureVitalSigns1, lblOptional);
        Validations.restartStylesV4(dateVitalSigns2, lblOptional);
        Validations.restartStyles(pressureVitalSigns, lblOptional);
        Validations.restartStyles(pulseVitalSigns2, lblOptional);
        Validations.restartStyles(temperatureVitalSigns2, lblOptional);
        Validations.restartStylesV4(dateVitalSigns3, lblOptional);
        Validations.restartStyles(pressureVitalSigns3, lblOptional);
        Validations.restartStyles(pulseVitalSigns3, lblOptional);
        Validations.restartStyles(temperatureVitalSigns3, lblOptional);
        Validations.restartStylesV4(dateVitalSigns4, lblOptional);
        Validations.restartStyles(pressureVitalSigns4, lblOptional);
        Validations.restartStyles(pulseVitalSigns4, lblOptional);
        Validations.restartStyles(temperatureVitalSigns4, lblOptional);
    }
    public void setDataExistence(String id) {
        String sql = "SELECT paternalLastname, maternalLastname, names FROM pacient WHERE idnumber = ?";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                txtNamesPacient.setText(resultSet.getString("names"));
                txtLastnamesPacient.setText(resultSet.getString("paternalLastname") + " " + resultSet.getString("maternalLastname"));
            }
        } catch (SQLException e) {
            System.out.println("Error in setDataBill : " + e.getMessage());
            e.printStackTrace();
        }
    }
    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateExistenceCodeClinic(txtCodeClinicHistory, lblClinicHistory, errors, "clinic_history");
        Validations.validateNumberClinic(txtNumberSheet, lblNumberSheet, errors);
        Validations.validateNoExistenceIdentityCard(lblCardNumber, txtCardNumber, errors, "pacient");
        Validations.validateNullsV2(txtReasonConsultation, lblConsultReason, errors);
        Validations.validateNullsV2(txtPersonalAntecedents, lblPersonalAntecedents, errors);
        Validations.validateNullsV2(txtFamiliarAntecedents, lblFamilyAntecedents, errors);
        Validations.validateNullsV2(txtDisease, lblDisease, errors);
        Validations.validateNullsV2(txtCurentRevision, lblCurrentRevision, errors);
        Validations.validateNullsV2(txtPhysycExam, lblPhysycExam, errors);
        Validations.validateNullsV2(txtPlans, lblPlans, errors);
        Validations.validateDateV1(txtDateControl, lblDateControl, errors);
        Validations.validateHour(txtHourControl, lblHourControl, errors);
        Validations.validateCompleteName(txtNameDoctor, lblNameDoctor, errors);
        Validations.validateDateV2(dateVitalSigns, lblOptional, errors);
        return errors;
    }
}
