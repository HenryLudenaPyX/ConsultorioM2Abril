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
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.Detail;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Message;
import com.prototype.cm2a.utils.Validations;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BillingGenerateController {

    @FXML
    private Button btnAdd;

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
    private Button btnQuit;

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
    private TableColumn<Detail, String> columnDescrption;

    @FXML
    private TableColumn<Detail, Integer> columnLine;

    @FXML
    private TableColumn<Detail, Integer> columnQuantity;

    @FXML
    private TableColumn<Detail, Double> columnTotalPrice;

    @FXML
    private TableColumn<Detail, Double> columnUnitPrice;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblAddressCustomer;

    @FXML
    private Label lblEmailCustomer;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblIdCustomer;

    @FXML
    private Label lblNameCustomer;

    @FXML
    private Label lblNameTypeService;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPhoneCustomer;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblSurnames;

    @FXML
    private Label lblTypeCustomer;

    @FXML
    private Label lblTypeService;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TableView<Detail> tableDetail;

    @FXML
    private TextField txtAddressCustomer;

    @FXML
    private TextField txtAdressOffice;

    @FXML
    private TextField txtEmailCustomer;

    @FXML
    private TextField txtEmisionDate;

    @FXML
    private TextField txtIdCustomer;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtNameCustomer;

    @FXML
    private TextField txtNameEmision;

    @FXML
    private TextField txtNameService;

    @FXML
    private TextField txtNumBill;

    @FXML
    private TextField txtOffice;

    @FXML
    private TextField txtPhoneCustomer;

    @FXML
    private TextField txtQuinatity;

    @FXML
    private TextField txtRUC;

    @FXML
    private TextField txtSubtotal;

    @FXML
    private TextField txtTotal;

    @FXML
    private ComboBox<String> txtTypeCustomer;
    private String[] typeCustomer = {"Paciente", "Cliente", "Consumidor final"};

    @FXML
    private ComboBox<String> txtTypeService;
    private String[] typeService = {"Medicamento", "Atención médica"};

    private String userRolAutentication;

    User userAutenticator = new User();

    Message message = new Message();

    List<String> errors = new ArrayList<>();

    public void setDataAutentication(User user) {
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

    private String idBranch;

    @FXML
    public void initialize() {
        setDataBill();
        txtTypeCustomer.getItems().addAll(typeCustomer);
        txtTypeService.getItems().addAll(typeService);
        txtNumBill.setText(generateSecuenceNumber());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        txtEmisionDate.setText(formattedDate);
        txtTypeCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(txtTypeCustomer.getSelectionModel().isSelected(0)) {
                resetStyles();
                resetCustomerFields();
                setEditableTextFields(false, true);
                txtIdCustomer.setOnAction(e -> {
                    Validations.validateNoExistenceIdentityCard(lblIdCustomer, txtIdCustomer, errors, "pacient");
                    setDataExistence(txtIdCustomer.getText());
                    txtNameCustomer.requestFocus();
                });
            } else if (txtTypeCustomer.getSelectionModel().isSelected(1)) {
                resetStyles();
                resetCustomerFields();
                txtIdCustomer.setOnAction(e -> {
                    Validations.validateIdentityCard(txtIdCustomer, lblIdCustomer, errors);
                    txtNameCustomer.requestFocus();
                });
                txtNameCustomer.setOnAction(e -> {
                    Validations.validateCompleteName(txtNameCustomer, lblNameCustomer, errors);
                    txtEmailCustomer.requestFocus();
                });
                txtEmailCustomer.setOnAction(e -> {
                    Validations.validateEmail(txtEmailCustomer, lblEmailCustomer, errors);
                    txtAddressCustomer.requestFocus();
                });
                txtAddressCustomer.setOnAction(e -> {
                    Validations.validateNulls(txtAddressCustomer, lblAddressCustomer, errors);
                    txtPhoneCustomer.requestFocus();
                });
                txtPhoneCustomer.setOnAction(e -> {
                    Validations.validatePhone(txtPhoneCustomer, lblPhoneCustomer, errors);
                });
                setEditableTextFields(true, true);
            } else if (txtTypeCustomer.getSelectionModel().isSelected(2)) {
                resetStyles();
                resetCustomerFields();
                setEditableTextFields(false, false);
                txtIdCustomer.setDisable(false);
                txtNameCustomer.setDisable(false);
                txtEmailCustomer.setDisable(false);
                txtAddressCustomer.setDisable(false);
                txtPhoneCustomer.setDisable(false);
            }
        });
        txtTypeService.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (txtTypeService.getSelectionModel().isSelected(0)) {
                suggestions = suggestionsMedicament();
                autoComplete = TextFields.bindAutoCompletion(txtNameService, suggestions);
            } else if (txtTypeService.getSelectionModel().isSelected(1)) {
                suggestions = suggestionsTypeAttention();
                autoComplete = TextFields.bindAutoCompletion(txtNameService, suggestions);
            } else {
                if (autoComplete != null) {
                    autoComplete.dispose();
                }
            }
        });
    }

    @FXML
    void cancelRegistr(ActionEvent event) {
        Validations.restartStyles(txtIdCustomer, lblIdCustomer);
        Validations.restartStyles(txtNameCustomer, lblNameCustomer);
        Validations.restartStyles(txtEmailCustomer, lblEmailCustomer);
        Validations.restartStyles(txtAddressCustomer, lblAddressCustomer);
        Validations.restartStyles(txtPhoneCustomer, lblPhoneCustomer);
        Validations.restartStyles(txtNameService, lblNameTypeService);
        Validations.restartStyles(txtQuinatity, lblQuantity);
        Validations.restartStylesV2(txtTypeService, lblTypeService);
        Validations.restartStylesV2(txtTypeCustomer, lblTypeCustomer);
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
        } catch (IOException e) {
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
        } catch (IOException e) {
            System.out.println("Error in openDashboardHome - BillingGenerateController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openGenerateBilling(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Administración") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/billing/billing-generate.fxml"));
                Parent root = loader.load();
                BillingGenerateController billingGenerateController = loader.getController();
                billingGenerateController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                System.out.println("Error in openGenerateBilling : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openQueryBilling : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openQueryClinicHistory : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
        } catch (IOException e) {
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
            } catch (IOException e) {
                System.out.println("Error in openQueryPacient : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error in openQueryParameter : " + e.getMessage());
            }
        } else {
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
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error in openQueryUser : " + e.getMessage());
            }
        } else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    @FXML
    void openRegisterClinicHistory(ActionEvent event) {
        if (userRolAutentication.equalsIgnoreCase("Medicina") || userRolAutentication.equalsIgnoreCase("Administrador")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/clinic-history-register.fxml"));
                Parent root = loader.load();
                ClinicHistoryRegisterController clinicHistoryRegisterController = loader.getController();
                clinicHistoryRegisterController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) menuModules.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            } catch (IOException e) {
                System.out.println("Error in openRegisterClinicHistory : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openRegisterMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openRegisterMedicament : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error in openRegisterPacient : " + e.getMessage());
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openRegisterParameter : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openRegisterTypeMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openRegisterUser : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
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
            } catch (IOException e) {
                System.out.println("Error in openSearchTypeMedicalAttention : " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            message.setWarning("No tiene los permisos necesarios");
        }
    }

    private ObservableList<Detail> detailList = FXCollections.observableArrayList();
    private AutoCompletionBinding<String> autoComplete;
    private Set<String> suggestions = new HashSet<>();
    private int currentLine = 1;
    List<String> errDetail = new ArrayList<>();
    @FXML
    void add(ActionEvent event) {
        Alert alert;
        errDetail = validateInputsDetail();
        if (errDetail.isEmpty()){
            if (txtTypeService.getSelectionModel().isSelected(0)) {
                String query = "SELECT unitPrice, stock FROM medicament WHERE name = ?";
                Connection connection = DBConnection.connection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, txtNameService.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    Detail detail;
                    while (resultSet.next()) {
                        String name = txtNameService.getText();
                        int quantity = Integer.parseInt(txtQuinatity.getText());
                        int stock = Integer.parseInt(resultSet.getString("stock"));
                        double unitPrice = Double.parseDouble(resultSet.getString("unitPrice"));
                        double totalPrice = quantity * unitPrice;
                        int updatedStock = stock - quantity;
                        unitPrice = Double.parseDouble(String.format("%.2f", unitPrice));
                        totalPrice = Double.parseDouble(String.format("%.2f", totalPrice));
                        if(stock >= 0 && updatedStock >= 0){
                            detail = new Detail(currentLine++, quantity, name, unitPrice, totalPrice);
                            detailList.add(detail);
                            showDetail();
                            actualizarMontoTotal();
                            txtTypeService.getSelectionModel().clearSelection();
                            txtNameService.clear();
                            txtQuinatity.clear();
                        }else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setHeaderText(null);
                            alert.setContentText("No hay la sufuciente cantidad de medicamento");
                            alert.showAndWait();
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error en añadir medicamento - Factura " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (txtTypeService.getSelectionModel().isSelected(1)) {
                String query = "SELECT unitPrice FROM type_attention WHERE name = ?";
                Connection connection = DBConnection.connection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, txtNameService.getText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    Detail detail;
                    while (resultSet.next()) {
                        String name = txtNameService.getText();
                        int quantity = Integer.parseInt(txtQuinatity.getText());
                        double unitPrice = Double.parseDouble(resultSet.getString("unitPrice"));
                        double totalPrice = quantity * unitPrice;
                        unitPrice = Double.parseDouble(String.format("%.2f", unitPrice));
                        totalPrice = Double.parseDouble(String.format("%.2f", totalPrice));
                        detail = new Detail(currentLine++, quantity, name, unitPrice, totalPrice);
                        detailList.add(detail);
                        showDetail();
                        actualizarMontoTotal();
                        txtTypeService.getSelectionModel().clearSelection();
                        txtNameService.clear();
                        txtQuinatity.clear();
                    }
                } catch (SQLException e) {
                    System.out.println("Error en añadir atencion medica - Factura " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Seleccione un tipo de servicio");
                alert.showAndWait();
            }
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Llene correctamente los campos");
            alert.showAndWait();
        }
    }
    public void showDetail() {
        columnLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnDescrption.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        columnTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tableDetail.setItems(detailList);
    }
    private void actualizarMontoTotal() {
        double subtotal = detailList.stream().mapToDouble(Detail::getTotalPrice).sum();
        txtSubtotal.setText(String.format("%.2f", subtotal));
        double iva = subtotal * 0.15;
        txtIva.setText(String.format("%.2f", iva));
        double total = subtotal + iva;
        txtTotal.setText(String.format("%.2f", total));
    }
    public static Set<String> suggestionsMedicament() {
        Set<String> suggestionMedicament = new HashSet<>();
        String query = "SELECT name FROM medicament";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                suggestionMedicament.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suggestionMedicament;
    }
    public static Set<String> suggestionsTypeAttention() {
        Set<String> suggestionTypeAttention = new HashSet<>();
        String query = "SELECT name FROM type_attention";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                suggestionTypeAttention.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suggestionTypeAttention;
    }

    @FXML
    void quit(ActionEvent event) {
        Detail selectedItem = tableDetail.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            detailList.remove(selectedItem);
            recalculateLines();
            actualizarMontoTotal();
        }
    }
    private void recalculateLines() {
        currentLine = 1;
        for (Detail detail : detailList) {
            detail.setLine(currentLine++);
        }
        tableDetail.refresh();
    }

    /*
    @FXML
    void saveRegister(ActionEvent event) {
        errors = validateInputs();
        List<String> err = validateInputsCf();
        String insertBill = "INSERT INTO bill (id, id_sucursal, type_costumer, id_customer, name_customer, address_customer, email_customer, phone_customer, emisionDate, total, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'activo')";
        String insertDetail = "INSERT INTO detailBill (lineD, id_bill, id_service, quantity, price) VALUES (?, ?, ?, ?, ?)";
        String updateMedicament = "UPDATE medicament SET stock = stock - ? WHERE id = ?";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (errors.isEmpty()) {
            try {
                if (txtTypeService.getSelectionModel().isSelected(2)) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje de confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("Estás seguro de guardar la factura?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement(insertBill);
                        preparedStatement.setString(1, txtNumBill.getText());
                        preparedStatement.setString(2, idBranch);
                        preparedStatement.setString(3, txtTypeCustomer.getValue());
                        preparedStatement.setString(4, null);
                        preparedStatement.setString(5, null);
                        preparedStatement.setString(6, null);
                        preparedStatement.setString(7, null);
                        preparedStatement.setString(8, null);
                        preparedStatement.setDate(9, java.sql.Date.valueOf(txtEmisionDate.getText()));
                        preparedStatement.setDouble(10, Double.parseDouble(txtTotal.getText()));
                        preparedStatement.executeUpdate();
                        PreparedStatement preparedUpdateMedicament = connection.prepareStatement(updateMedicament);
                        PreparedStatement prepareDetail = connection.prepareStatement(insertDetail);
                        for (Detail detail : detailList) {
                            if (checkMedicament(detail.getDescription())) {
                                String obtainCodeMedicament = "SELECT id FROM medicament WHERE name = ?";
                                PreparedStatement prepareObtainMedicament = connection.prepareStatement(obtainCodeMedicament);
                                prepareObtainMedicament.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainMedicament.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                    preparedUpdateMedicament.setInt(2, code);
                                    preparedUpdateMedicament.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID del medicamento para " + detail.getDescription());
                                }
                            } else {
                                String obtainCodeAttention = "SELECT id FROM type_attention WHERE name = ?";
                                PreparedStatement prepareObtainAttention = connection.prepareStatement(obtainCodeAttention);
                                prepareObtainAttention.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainAttention.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                    preparedUpdateMedicament.setInt(2, code);
                                    preparedUpdateMedicament.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID de la atencion para " + detail.getDescription());
                                }
                            }
                        }
                        prepareDetail.executeBatch();
                        preparedUpdateMedicament.executeBatch();
                        connection.commit();
                        textFieldReset();
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Mensaje de confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura se ha guardado exitosamente");
                        alert.showAndWait();
                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura no se ha generado");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje de confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("Estás seguro de guardar la factura?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement(insertBill);
                        preparedStatement.setString(1, txtNumBill.getText());
                        preparedStatement.setString(2, idBranch);
                        preparedStatement.setString(3, txtTypeCustomer.getValue());
                        preparedStatement.setString(4, txtIdCustomer.getText());
                        preparedStatement.setString(5, txtNameCustomer.getText());
                        preparedStatement.setString(6, txtAddressCustomer.getText());
                        preparedStatement.setString(7, txtEmailCustomer.getText());
                        preparedStatement.setString(8, txtPhoneCustomer.getText());
                        preparedStatement.setDate(9, java.sql.Date.valueOf(txtEmisionDate.getText()));
                        preparedStatement.setDouble(10, Double.parseDouble(txtTotal.getText()));
                        preparedStatement.executeUpdate();
                        PreparedStatement preparedUpdateMedicament = connection.prepareStatement(updateMedicament);
                        PreparedStatement prepareDetail = connection.prepareStatement(insertDetail);
                        for (Detail detail : detailList) {
                            if (checkMedicament(detail.getDescription())) {
                                String obtainCodeMedicament = "SELECT id FROM medicament WHERE name = ?";
                                PreparedStatement prepareObtainMedicament = connection.prepareStatement(obtainCodeMedicament);
                                prepareObtainMedicament.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainMedicament.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                    preparedUpdateMedicament.setInt(2, code);
                                    preparedUpdateMedicament.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID del medicamento para " + detail.getDescription());
                                }
                            } else {
                                String obtainCodeAttention = "SELECT id FROM type_attention WHERE name = ?";
                                PreparedStatement prepareObtainAttention = connection.prepareStatement(obtainCodeAttention);
                                prepareObtainAttention.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainAttention.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                    preparedUpdateMedicament.setInt(2, code);
                                    preparedUpdateMedicament.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID de la atención para " + detail.getDescription());
                                }
                            }
                        }
                        prepareDetail.executeBatch();
                        preparedUpdateMedicament.executeBatch();
                        connection.commit();
                        textFieldReset();
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Mensaje de confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura se ha guardado exitosamente");
                        alert.showAndWait();
                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura no se ha generado");
                        alert.showAndWait();
                    }
                }
            }catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    System.out.println("Error en rollback: " + rollbackEx.getMessage());
                    rollbackEx.printStackTrace();
                }
                System.out.println("Error en generar factura: " + e.getMessage());
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
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Llene los datos correctamente");
            alert.showAndWait();
        }
    }
    */

    List<String> err = new ArrayList<>();
    @FXML
    void saveRegister(ActionEvent event) {
        errors = validateInputs();
        err = validateInputsCf();
        String insertBill = "INSERT INTO bill (id, id_sucursal, type_customer, id_customer, name_customer, address_customer, email_customer, phone_customer, emisionDate, total, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'activo')";
        String insertDetail = "INSERT INTO detailBill (lineD, id_bill, id_service, quantity, price) VALUES (?, ?, ?, ?, ?)";
        String updateMedicament = "UPDATE medicament SET stock = stock - ? WHERE id = ?";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (err.isEmpty() && txtTypeCustomer.getSelectionModel().isSelected(2)){
            resetStyles();
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje de confirmación");
                alert.setHeaderText(null);
                alert.setContentText("Estás seguro de guardar la factura?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(insertBill);
                    preparedStatement.setString(1, txtNumBill.getText());
                    preparedStatement.setString(2, idBranch);
                    preparedStatement.setString(3, txtTypeCustomer.getValue());
                    preparedStatement.setString(4, null);
                    preparedStatement.setString(5, null);
                    preparedStatement.setString(6, null);
                    preparedStatement.setString(7, null);
                    preparedStatement.setString(8, null);
                    String formattedDateString = txtEmisionDate.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(formattedDateString, formatter);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                    preparedStatement.setDate(9, sqlDate);
                    preparedStatement.setDouble(10, Double.parseDouble(txtTotal.getText()));
                    preparedStatement.executeUpdate();
                    PreparedStatement preparedUpdateMedicament = connection.prepareStatement(updateMedicament);
                    PreparedStatement prepareDetail = connection.prepareStatement(insertDetail);
                    for (Detail detail : detailList) {
                        if (checkMedicament(detail.getDescription())) {
                            String obtainCodeMedicament = "SELECT id FROM medicament WHERE name = ?";
                            PreparedStatement prepareObtainMedicament = connection.prepareStatement(obtainCodeMedicament);
                            prepareObtainMedicament.setString(1, detail.getDescription());
                            ResultSet resultSet = prepareObtainMedicament.executeQuery();
                            if (resultSet.next()) {
                                int code = Integer.parseInt(resultSet.getString("id"));
                                prepareDetail.setInt(1, detail.getLine());
                                prepareDetail.setString(2, txtNumBill.getText());
                                prepareDetail.setInt(3, code);
                                prepareDetail.setInt(4, detail.getQuantity());
                                prepareDetail.setDouble(5, detail.getTotalPrice());
                                prepareDetail.addBatch();
                                preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                preparedUpdateMedicament.setInt(2, code);
                                preparedUpdateMedicament.addBatch();
                            } else {
                                throw new SQLException("No se encontró el ID del medicamento para " + detail.getDescription());
                            }
                        } else {
                            String obtainCodeAttention = "SELECT id FROM type_attention WHERE name = ?";
                            PreparedStatement prepareObtainAttention = connection.prepareStatement(obtainCodeAttention);
                            prepareObtainAttention.setString(1, detail.getDescription());
                            ResultSet resultSet = prepareObtainAttention.executeQuery();
                            if (resultSet.next()) {
                                int code = Integer.parseInt(resultSet.getString("id"));
                                prepareDetail.setInt(1, detail.getLine());
                                prepareDetail.setString(2, txtNumBill.getText());
                                prepareDetail.setInt(3, code);
                                prepareDetail.setInt(4, detail.getQuantity());
                                prepareDetail.setDouble(5, detail.getTotalPrice());
                                prepareDetail.addBatch();
                            } else {
                                throw new SQLException("No se encontró el ID de la atencion para " + detail.getDescription());
                            }
                        }
                    }
                    prepareDetail.executeBatch();
                    preparedUpdateMedicament.executeBatch();
                    connection.commit();
                    textFieldReset();
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje de confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("La factura se ha guardado exitosamente");
                    alert.showAndWait();
                    currentLine = 1;
                    txtNumBill.setText(generateSecuenceNumber());
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("La factura no se ha generado");
                    alert.showAndWait();
                }
            }catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    System.out.println("Error en rollback: " + rollbackEx.getMessage());
                    rollbackEx.printStackTrace();
                }
                System.out.println("Error en generar factura: " + e.getMessage());
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
        } else if (errors.isEmpty()) {
            try {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje de confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("Estás seguro de guardar la factura?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)) {
                        connection.setAutoCommit(false);
                        PreparedStatement preparedStatement = connection.prepareStatement(insertBill);
                        preparedStatement.setString(1, txtNumBill.getText());
                        preparedStatement.setString(2, idBranch);
                        preparedStatement.setString(3, txtTypeCustomer.getValue());
                        preparedStatement.setString(4, txtIdCustomer.getText());
                        preparedStatement.setString(5, txtNameCustomer.getText());
                        preparedStatement.setString(6, txtAddressCustomer.getText());
                        preparedStatement.setString(7, txtEmailCustomer.getText());
                        preparedStatement.setString(8, txtPhoneCustomer.getText());
                        String formattedDateString = txtEmisionDate.getText();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate localDate = LocalDate.parse(formattedDateString, formatter);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                        preparedStatement.setDate(9, sqlDate);
                        preparedStatement.setDouble(10, Double.parseDouble(txtTotal.getText()));
                        preparedStatement.executeUpdate();
                        PreparedStatement preparedUpdateMedicament = connection.prepareStatement(updateMedicament);
                        PreparedStatement prepareDetail = connection.prepareStatement(insertDetail);
                        for (Detail detail : detailList) {
                            if (checkMedicament(detail.getDescription())) {
                                String obtainCodeMedicament = "SELECT id FROM medicament WHERE name = ?";
                                PreparedStatement prepareObtainMedicament = connection.prepareStatement(obtainCodeMedicament);
                                prepareObtainMedicament.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainMedicament.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    prepareDetail.addBatch();
                                    preparedUpdateMedicament.setDouble(1, detail.getTotalPrice());
                                    preparedUpdateMedicament.setInt(2, code);
                                    preparedUpdateMedicament.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID del medicamento para " + detail.getDescription());
                                }
                            } else {
                                String obtainCodeAttention = "SELECT id FROM type_attention WHERE name = ?";
                                PreparedStatement prepareObtainAttention = connection.prepareStatement(obtainCodeAttention);
                                prepareObtainAttention.setString(1, detail.getDescription());
                                ResultSet resultSet = prepareObtainAttention.executeQuery();
                                if (resultSet.next()) {
                                    int code = Integer.parseInt(resultSet.getString("id"));
                                    prepareDetail.setInt(1, detail.getLine());
                                    prepareDetail.setString(2, txtNumBill.getText());
                                    prepareDetail.setInt(3, code);
                                    prepareDetail.setInt(4, detail.getQuantity());
                                    prepareDetail.setDouble(5, detail.getTotalPrice());
                                    prepareDetail.addBatch();
                                } else {
                                    throw new SQLException("No se encontró el ID de la atención para " + detail.getDescription());
                                }
                            }
                        }
                        prepareDetail.executeBatch();
                        preparedUpdateMedicament.executeBatch();
                        connection.commit();
                        textFieldReset();
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Mensaje de confirmación");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura se ha guardado exitosamente");
                        alert.showAndWait();
                        currentLine = 1;
                        txtNumBill.setText(generateSecuenceNumber());
                    }else{
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText(null);
                        alert.setContentText("La factura no se ha generado");
                        alert.showAndWait();
                    }
            }catch (SQLException e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    System.out.println("Error en rollback: " + rollbackEx.getMessage());
                    rollbackEx.printStackTrace();
                }
                System.out.println("Error en generar factura: " + e.getMessage());
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
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Llene los datos correctamente");
            alert.showAndWait();
        }
    }

    public void resetStyles(){
        Validations.restartStyles(txtIdCustomer, lblIdCustomer);
        Validations.restartStyles(txtNameCustomer, lblNameCustomer);
        Validations.restartStyles(txtEmailCustomer, lblEmailCustomer);
        Validations.restartStyles(txtAddressCustomer, lblAddressCustomer);
        Validations.restartStyles(txtPhoneCustomer, lblPhoneCustomer);
    }
    public void resetCustomerFields(){
        txtIdCustomer.clear();
        txtNameCustomer.clear();
        txtEmailCustomer.clear();
        txtAddressCustomer.clear();
        txtPhoneCustomer.clear();
    }
    public void setEditableTextFields(Boolean arg, Boolean ar){
        txtIdCustomer.setEditable(ar);
        txtNameCustomer.setEditable(arg);
        txtAddressCustomer.setEditable(arg);
        txtPhoneCustomer.setEditable(arg);
        txtEmailCustomer.setEditable(arg);
    }
    public void setDataExistence(String id){
        String sql = "SELECT paternalLastname, maternalLastname, names, address, phonenumber, email FROM pacient WHERE idnumber = ?";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                txtNameCustomer.setText(resultSet.getString("names") + " " + resultSet.getString("paternalLastname") + " " + resultSet.getString("maternalLastname"));
                txtAddressCustomer.setText(resultSet.getString("address"));
                txtPhoneCustomer.setText(resultSet.getString("phonenumber"));
                txtEmailCustomer.setText(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error in setDataBill : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static boolean checkMedicament (String name){
        String query = "SELECT EXISTS (SELECT 1 FROM medicament WHERE name = ?)";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkMedicament: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public void setDataBill () {
        String sql = "SELECT id, name, address, ruc, nameOwner FROM branch";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idBranch = resultSet.getString("id");
                txtOffice.setText(resultSet.getString("name"));
                txtAdressOffice.setText(resultSet.getString("address"));
                txtRUC.setText(resultSet.getString("ruc"));
                txtNameEmision.setText(resultSet.getString("nameOwner"));
            }
        } catch (SQLException e) {
            System.out.println("Error in setDataBill : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void textFieldReset () {
        txtTypeCustomer.getSelectionModel().clearSelection();
        txtIdCustomer.clear();
        txtNameCustomer.clear();
        txtEmailCustomer.clear();
        txtAddressCustomer.clear();
        txtPhoneCustomer.clear();
        txtTypeService.getSelectionModel().clearSelection();
        txtNameService.clear();
        txtQuinatity.clear();
        txtSubtotal.clear();
        txtIva.clear();
        txtTotal.clear();
        detailList.clear();
        tableDetail.refresh();
    }
    private List<String> validateInputs () {
        List<String> errors = new ArrayList<>();
        Validations.validateIdentityCard(txtIdCustomer, lblIdCustomer, errors);
        Validations.validateCompleteName(txtNameCustomer, lblNameCustomer, errors);
        Validations.validateNulls(txtAddressCustomer, lblAddressCustomer, errors);
        Validations.validatePhone(txtPhoneCustomer, lblPhoneCustomer, errors);
        Validations.validateEmail(txtEmailCustomer, lblEmailCustomer, errors);
        Validations.validateComboBox(txtTypeCustomer, lblTypeCustomer, errors);
        if(txtTotal.getText().isEmpty() || txtTotal.getText().isEmpty() || Double.parseDouble(txtTotal.getText()) == 0.00){
            errors.add("Error in price");
        }
        return errors;
    }
    private List<String> validateInputsDetail () {
        List<String> errorDetail = new ArrayList<>();
        Validations.validateStock(txtQuinatity, lblQuantity, errorDetail);
        Validations.validateComboBox(txtTypeService, lblTypeService, errorDetail);
        Validations.validateNulls(txtNameService, lblNameTypeService, errorDetail);
        return errorDetail;
    }
    private List<String> validateInputsCf () {
        List<String> error = new ArrayList<>();
        Validations.validateComboBox(txtTypeCustomer, lblTypeCustomer, errors);
        if(txtTotal.getText().isEmpty() || txtTotal.getText().isEmpty() || Double.parseDouble(txtTotal.getText()) == 0.00){
            error.add("Error in price");
        }
        return error;
    }
    public String generateSecuenceNumber (){
        long secuenceNumber = 1;
        String sql = "SELECT MAX(id) FROM bill";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                secuenceNumber = resultSet.getLong(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error al leer último número secuencial: " + e.getMessage());
            e.printStackTrace();
        }
        return String.format("%010d", secuenceNumber);
    }
    private boolean numeroYaExiste(String numeroSecuencial) {
        String query = "SELECT EXISTS (SELECT 1 FROM bill WHERE id = ?)";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, numeroSecuencial);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkExistenceIdentityCard: " + e.getMessage());
        }
        return false;
    }
}