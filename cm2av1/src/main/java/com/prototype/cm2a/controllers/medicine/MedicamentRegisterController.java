package com.prototype.cm2a.controllers.medicine;

import com.prototype.cm2a.controllers.billing.BillingGenerateController;
import com.prototype.cm2a.controllers.billing.BillingSearchController;
import com.prototype.cm2a.controllers.history.*;
import com.prototype.cm2a.controllers.pacient.PacientRegisterController;
import com.prototype.cm2a.controllers.pacient.PacientSearchController;
import com.prototype.cm2a.controllers.parameter.ParameterRegisterController;
import com.prototype.cm2a.controllers.parameter.ParameterSearchController;
import com.prototype.cm2a.controllers.principalwindows.DashboardController;
import com.prototype.cm2a.controllers.user.UserRegisterController;
import com.prototype.cm2a.controllers.user.UserSearchController;
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicamentRegisterController {

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
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private MenuItem btnRegisterUser;

    @FXML
    private Button btnSave;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblAditionalInformation;

    @FXML
    private Label lblExpirationDate;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblMedicamentCode;

    @FXML
    private Label lblMedicamentName;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblSurnames;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblFabricationDate;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TextField txtAdicionalInformation;

    @FXML
    private TextField txtAge;

    @FXML
    private DatePicker txtExpirationDate;

    @FXML
    private DatePicker txtFabricationDate;

    @FXML
    private TextField txtIce;

    @FXML
    private HBox txtIceCode;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtMedicamentCode;

    @FXML
    private TextField txtMedicamentName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtTurism;

    @FXML
    private TextField txtUnitPrice;

    private String userRolAutentication;

    Message message = new Message();

    User userAutenticator = new User();

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
        String selectParameters = "SELECT ivaTarife, gravaIce, codeIce, turism FROM parameters";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatementSelect = connection.prepareStatement(selectParameters);
            ResultSet resultSetSelect = preparedStatementSelect.executeQuery();
            while (resultSetSelect.next()){
                txtIva.setText(resultSetSelect.getString("ivaTarife"));
                txtIce.setText(resultSetSelect.getString("gravaIce"));
                txtAge.setText(resultSetSelect.getString("codeIce"));
                txtTurism.setText(resultSetSelect.getString("turism"));
            }
        } catch (SQLException e) {
            System.out.println("Error in ParameterRegisterController" + e.getMessage());
            e.printStackTrace();
        }
        txtMedicamentCode.setOnAction(e -> {
            txtMedicamentName.requestFocus();
            Validations.validateExistenceCode(txtMedicamentCode, lblMedicamentCode, errors, "medicament");
        });
        txtMedicamentName.setOnAction(e -> {
            txtAdicionalInformation.requestFocus();
            Validations.validateNulls(txtMedicamentName, lblMedicamentName, errors);
        });
        txtAdicionalInformation.setOnAction(e -> {
            txtPrice.requestFocus();
            Validations.validateNulls(txtAdicionalInformation, lblAditionalInformation, errors);
        });
        txtPrice.setOnAction(e -> {
            txtUnitPrice.requestFocus();
            Validations.validatePrice(txtPrice, lblPrice, errors);
        });
        txtUnitPrice.setOnAction(e -> {
            txtStock.requestFocus();
            Validations.validatePrice(txtUnitPrice, lblUnitPrice, errors);
        });
        txtStock.setOnAction(e -> {
            txtFabricationDate.requestFocus();
            Validations.validateStock(txtStock, lblStock, errors);
        });
        txtFabricationDate.setOnAction(e -> {
            txtExpirationDate.requestFocus();
            txtFabricationDate.setConverter(new StringConverter<LocalDate>() {
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
            Validations.validateDateV2(txtFabricationDate, lblFabricationDate, errors);
        });
        txtExpirationDate.setOnAction(e -> {
            txtExpirationDate.setConverter(new StringConverter<LocalDate>() {
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
            Validations.validateDateV1(txtExpirationDate, lblExpirationDate, errors);
        });
    }

    @FXML
    void cancelRegister(ActionEvent event) {

        textFieldReset();
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
        List<String> errors = validateInputs();
        String insertMedicament = "INSERT INTO medicament (id, name, aditional, providerPrice, unitPrice, stock, fabricationDate, expirationDate, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'activo')";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (errors.isEmpty()){
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("MENSAJE DE CONFIRMACIÓN");
                alert.setHeaderText(null);
                alert.setContentText("¿Estás seguro de guardar los datos del medicamento?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    PreparedStatement preparedStatement = connection.prepareStatement(insertMedicament);
                    preparedStatement.setInt(1, Integer.parseInt(txtMedicamentCode.getText()));
                    preparedStatement.setString(2, txtMedicamentName.getText());
                    preparedStatement.setString(3, txtAdicionalInformation.getText());
                    preparedStatement.setDouble(4, Double.parseDouble(txtPrice.getText()));
                    preparedStatement.setDouble(5, Double.parseDouble(txtUnitPrice.getText()));
                    preparedStatement.setInt(6, Integer.parseInt(txtStock.getText()));
                    preparedStatement.setDate(7, Date.valueOf(txtFabricationDate.getValue()));
                    preparedStatement.setDate(8, Date.valueOf(txtExpirationDate.getValue()));
                    preparedStatement.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("¡INFORMACIÓN!");
                    alert.setHeaderText(null);
                    alert.setContentText("Medicamento registrado con éxito");
                    alert.showAndWait();
                    textFieldReset();
                }
            } catch (SQLException e) {
                System.out.println("Error in saveRegisterMedicament : " + e.getMessage());
                e.printStackTrace();
            }
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("¡ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Llene los datos correctamente");
            alert.showAndWait();
        }
    }

    public void textFieldReset(){
        txtMedicamentCode.clear();
        txtMedicamentName.clear();
        txtAdicionalInformation.clear();
        txtPrice.clear();
        txtUnitPrice.clear();
        txtStock.clear();
        txtFabricationDate.getEditor().clear();
        txtExpirationDate.getEditor().clear();
    }
    public void setParameters(){
        String queryParameters = "SELECT FROM parameters";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryParameters);
        } catch (SQLException e) {
            System.out.println("Error in setParametersMedicament : " + e.getMessage());
            e.printStackTrace();
        }
    }
    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateExistenceCode(txtMedicamentCode, lblMedicamentCode, errors, "medicament");
        Validations.validateNulls(txtMedicamentName, lblMedicamentName, errors);
        Validations.validateNulls(txtAdicionalInformation, lblAditionalInformation, errors);
        Validations.validatePrice(txtPrice, lblPrice, errors);
        Validations.validatePrice(txtUnitPrice, lblUnitPrice, errors);
        Validations.validateStock(txtStock, lblStock, errors);
        Validations.validateDateV2(txtFabricationDate, lblFabricationDate, errors);
        Validations.validateDateV1(txtExpirationDate, lblExpirationDate, errors);
        return errors;
    }
}
