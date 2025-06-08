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

public class MedicalAttentionRegisterController {

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
    private HBox hBoxRectangle;

    @FXML
    private Label lblCardNumberAtte;

    @FXML
    private Label lblDateAttention;

    @FXML
    private Label lblEvolution;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblHourAttention;

    @FXML
    private Label lblMedication;

    @FXML
    private Label lblNameMedic;

    @FXML
    private Label lblNameMedic1;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblPreinscription;

    @FXML
    private Label lblSurnames;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TextField txtCradNumberAtte;

    @FXML
    private DatePicker txtDateAttention;

    @FXML
    private TextArea txtEvolution;

    @FXML
    private TextField txtHourAttention;

    @FXML
    private TextArea txtMedicaments;

    @FXML
    private TextField txtNameMedic;

    @FXML
    private TextField txtNameMedic1;

    @FXML
    private TextArea txtPreinscription;

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
        Functions.updateDateTime(lblHour);
        txtCradNumberAtte.setOnAction(e -> {
            Validations.validateNoExistenceIdentityCard(lblCardNumberAtte, txtCradNumberAtte, errors, "pacient");
            txtDateAttention.requestFocus();
        });
        txtDateAttention.setOnAction(e -> {
            txtHourAttention.requestFocus();
            txtDateAttention.setConverter(new StringConverter<LocalDate>() {
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
            Validations.validateDateV2(txtDateAttention, lblDateAttention, errors);
        });
        txtHourAttention.setOnAction(e -> {
            Validations.validateHour(txtHourAttention, lblHourAttention, errors);
            txtEvolution.requestFocus();
        });
        txtNameMedic.setOnAction(e -> {
            Validations.validateCompleteName(txtNameMedic, lblNameMedic, errors);
            txtNameMedic1.requestFocus();
        });
        txtNameMedic1.setOnAction(e -> {
            Validations.validateCompleteName(txtNameMedic, lblNameMedic, errors);
        });
    }

    @FXML
    void cancelRegistr(ActionEvent event) {
        resetTextFields();
        resetStyles();
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

    String clinicHistoryCode;
    @FXML
    void saveRegister(ActionEvent event) {
        errors = validateInputs();
        Alert alert;
        String insertMedicalAttention = "INSERT INTO madical_attention (id_pacient, date_attention, hour_attention, evolution, prescription, medication, name_medic) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String selectCodeHistory = "SELECT id FROM clinic_history WHERE idnumber = ?";
        Connection connection = DBConnection.connection();
        if (errors.isEmpty()) {
            if(txtNameMedic.getText().equalsIgnoreCase(txtNameMedic1.getText())){
                try {
                    PreparedStatement preparedStatementSelect = connection.prepareStatement(selectCodeHistory);
                    preparedStatementSelect.setString(1, txtCradNumberAtte.getText());
                    ResultSet resultSetSelect = preparedStatementSelect.executeQuery();
                    while (resultSetSelect.next()) {
                        clinicHistoryCode = resultSetSelect.getString("id");
                    }
                    if(!Validations.checkExistence(txtCradNumberAtte.getText(), clinicHistoryCode, "clinic_history")) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Mensaje de confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("Estás seguro de guardar la Atención Médica?");
                        Optional<ButtonType> option = alert.showAndWait();
                        if (option.get().equals(ButtonType.OK)) {
                            PreparedStatement preparedStatementInsert = connection.prepareStatement(insertMedicalAttention);
                            preparedStatementInsert.setString(1, txtCradNumberAtte.getText());
                            preparedStatementInsert.setDate(2, Date.valueOf(txtDateAttention.getValue()));
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                            LocalTime localAttentionTime = LocalTime.parse(txtHourAttention.getText(), timeFormatter);
                            preparedStatementInsert.setTime(3, Time.valueOf(localAttentionTime));
                            preparedStatementInsert.setString(4, txtEvolution.getText());
                            preparedStatementInsert.setString(5, txtPreinscription.getText());
                            preparedStatementInsert.setString(6, txtMedicaments.getText());
                            preparedStatementInsert.setString(7, txtNameMedic.getText());
                            preparedStatementInsert.executeUpdate();
                            resetTextFields();
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("La Atención Médica se ha registrado exitosamente");
                            alert.showAndWait();
                        }
                    }else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("El paciente no tiene una historia clínica registrada");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    System.out.println("Error in saveRegister - Medical Attention : " + e.getMessage());
                    e.printStackTrace();
                }
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Llene los datos correctamente");
                alert.showAndWait();
            }
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Llene los datos correctamente");
            alert.showAndWait();
        }
    }

    public void resetTextFields(){
        txtCradNumberAtte.clear();
        txtDateAttention.getEditor().clear();
        txtHourAttention.clear();
        txtEvolution.clear();
        txtPreinscription.clear();
        txtMedicaments.clear();
        txtNameMedic.clear();
        txtNameMedic.clear();
    }

    public void resetStyles(){
        Validations.restartStyles(txtCradNumberAtte, lblCardNumberAtte);
        Validations.restartStylesV4(txtDateAttention, lblDateAttention);
        Validations.restartStyles(txtHourAttention, lblHourAttention);
        Validations.restartStylesV3(txtEvolution, lblEvolution);
        Validations.restartStylesV3(txtPreinscription, lblPreinscription);
        Validations.restartStylesV3(txtMedicaments, lblMedication);
        Validations.restartStyles(txtNameMedic, lblNameMedic);
        Validations.restartStyles(txtNameMedic1, lblNameMedic1);
    }

    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateNoExistenceIdentityCard(lblCardNumberAtte, txtCradNumberAtte, errors, "pacient");
        Validations.validateDateV2(txtDateAttention, lblDateAttention, errors);
        Validations.validateHour(txtHourAttention, lblHourAttention, errors);
        Validations.validateNullsV2(txtEvolution, lblEvolution, errors);
        Validations.validateNullsV2(txtPreinscription, lblPreinscription, errors);
        Validations.validateNullsV2(txtMedicaments, lblMedication, errors);
        Validations.validateCompleteName(txtNameMedic, lblNameMedic, errors);
        Validations.validateCompleteName(txtNameMedic1, lblNameMedic1, errors);
        return errors;
    }
}
