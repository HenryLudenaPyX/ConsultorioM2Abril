package com.prototype.cm2a.controllers.pacient;

import com.prototype.cm2a.controllers.billing.BillingGenerateController;
import com.prototype.cm2a.controllers.billing.BillingSearchController;
import com.prototype.cm2a.controllers.history.*;
import com.prototype.cm2a.controllers.medicine.MedicamentRegisterController;
import com.prototype.cm2a.controllers.medicine.MedicamentSearchController;
import com.prototype.cm2a.controllers.parameter.ParameterRegisterController;
import com.prototype.cm2a.controllers.parameter.ParameterSearchController;
import com.prototype.cm2a.controllers.principalwindows.DashboardController;
import com.prototype.cm2a.controllers.user.UserRegisterController;
import com.prototype.cm2a.controllers.user.UserSearchController;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Functions;
import com.prototype.cm2a.utils.Message;
import com.prototype.cm2a.utils.Query;
import com.prototype.cm2a.utils.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacientRegisterController {

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
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private Button btnSave;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblAdress;

    @FXML
    private Label lblAdressPeople;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblArrivalMethod;

    @FXML
    private Label lblBirthDate;

    @FXML
    private Label lblCanton;

    @FXML
    private Label lblDateAttention;

    @FXML
    private Label lblFirstSurname;

    @FXML
    private Label lblHealthAssurance;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblHourAttention;

    @FXML
    private Label lblIdentityCard;

    @FXML
    private Label lblInformationSource;

    @FXML
    private Label lblInstitutionOrPeople;

    @FXML
    private Label lblInstitutionPhone;

    @FXML
    private Label lblInstruction;

    @FXML
    private Label lblLastSurname;

    @FXML
    private Label lblMail;

    @FXML
    private Label lblMailPeople;

    @FXML
    private Label lblMaritalStatus;

    @FXML
    private Label lblNamePeople;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblNamesE;

    @FXML
    private Label lblNationality;

    @FXML
    private Label lblOcupation;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPhoneNumber;

    @FXML
    private Label lblPhonePeople;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblProvince;

    @FXML
    private Label lblRelationship;

    @FXML
    private Label lblSex;

    @FXML
    private Label lblSurnames;

    @FXML
    private MenuBar menuModules;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAddressPeople;

    @FXML
    private TextField txtAge;

    @FXML
    private ComboBox<String> txtArrivalMethod;
    private String[] arrivalMethod = {"Ambulatorio", "Silla de ruedas", "Camilla"};

    @FXML
    private DatePicker txtBirthDate;

    @FXML
    private TextField txtCanton;

    @FXML
    private ComboBox<String> txtCivilStatus;
    private String[] civilStatus = {"Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a", "Unión libre"};

    @FXML
    private DatePicker txtDateAttention;

    @FXML
    private TextField txtFirstSurname;

    @FXML
    private ComboBox<String> txtFontInformation;
    private String[] fontInformation = {"Directo", "Indirecto"};

    @FXML
    private ComboBox<String> txtHealthInsurance;
    private String[] healthInsurance = {"IESS","ISSFA", "ISSPOL", "MSP", "SPPAT", "Privado", "Otro", "Ninguno"};

    @FXML
    private TextField txtHourAttention;

    @FXML
    private TextField txtIdentityCard;

    @FXML
    private TextField txtInstitutionOrPeople;

    @FXML
    private TextField txtInstitutionOrPeoplePhone;

    @FXML
    private ComboBox<String> txtInstruction;
    private String[] instruction = {"Sin instrucción", "Basica", "Bachillerato", "Superior", "Especial"};

    @FXML
    private TextField txtLastSurname;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtMailPeople;

    @FXML
    private TextField txtNamePeople;

    @FXML
    private TextField txtNames;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtOcupation;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPhonePeople;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtRelationship;
    private String[] relationship = {"Padre/Madre", "Abuelo/a", "Hermano/a", "Tio/a", "Primo/a", "Otro"};

    @FXML
    private ComboBox<String> txtSex;
    private String[] sex = {"Masculino", "Femenino"};

    List<String> errors = new ArrayList<>();

    private String userRolAutentication;

    Message message = new Message();

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
    public void initialize(){
        Functions.updateDateTime(lblHour);
        txtArrivalMethod.getItems().addAll(arrivalMethod);
        txtCivilStatus.getItems().addAll(civilStatus);
        txtFontInformation.getItems().addAll(fontInformation);
        txtHealthInsurance.getItems().addAll(healthInsurance);
        txtInstruction.getItems().addAll(instruction);
        txtSex.getItems().addAll(sex);
        txtIdentityCard.setOnAction(e -> {
            txtFirstSurname.requestFocus();
            Validations.validateExistenceIdentityCard(lblIdentityCard, txtIdentityCard, errors, "Pacient");
        });
        txtFirstSurname.setOnAction(e -> {
            txtLastSurname.requestFocus();
            Validations.validateOneWord(txtFirstSurname, lblFirstSurname, errors);
        });
        txtLastSurname.setOnAction(e -> {
            txtNames.requestFocus();
            Validations.validateOneWord(txtLastSurname, lblLastSurname, errors);
        });
        txtNames.setOnAction(e -> {
            txtNationality.requestFocus();
            Validations.validateTwoWords(txtNames, lblNamesE, errors);
        });
        txtNationality.setOnAction(e -> {
            txtBirthDate.requestFocus();
            Validations.validateOneWord(txtNationality, lblNationality, errors);
        });
        txtBirthDate.showingProperty().addListener((obs, wasShowing, isShowing) -> {
            if (isShowing) {
                txtBirthDate.hide();
            }
        });
        txtBirthDate.setOnAction(e -> {
            txtAddress.requestFocus();
            txtBirthDate.setConverter(new StringConverter<LocalDate>() {
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
            Validations.validateDateV2(txtBirthDate, lblBirthDate, errors);
        });
        txtAddress.setOnAction(e -> {
            txtCanton.requestFocus();
            Validations.validateNulls(txtAddress, lblAdress, errors);
        });
        txtCanton.setOnAction(e -> {
            txtProvince.requestFocus();
            Validations.validateWords(txtCanton, lblCanton, errors);
        });
        txtProvince.setOnAction(e -> {
            txtPhone.requestFocus();
            Validations.validateWords(txtProvince, lblProvince, errors);
        });
        txtPhone.setOnAction(e -> {
            txtMail.requestFocus();
            Validations.validatePhone(txtPhone, lblPhoneNumber, errors);
        });
        txtMail.setOnAction(e -> {
            txtDateAttention.requestFocus();
            Validations.validateEmail(txtMail, lblMail, errors);
        });

        txtDateAttention.showingProperty().addListener((obs, wasShowing, isShowing) -> {
            if (isShowing) {
                txtDateAttention.hide();
            }
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
            txtAge.requestFocus();
            Validations.validateHour(txtHourAttention, lblHourAttention, errors);
        });
        txtAge.setOnAction(e -> {
            txtSex.requestFocus();
            Validations.validateAgeV2(txtBirthDate, txtAge, lblAge, errors);
        });
        txtSex.setOnAction(e -> {
            txtCivilStatus.requestFocus();
            Validations.validateComboBox(txtSex, lblSex, errors);
        });
        txtCivilStatus.setOnAction(e -> {
            txtInstruction.requestFocus();
            Validations.validateComboBox(txtCivilStatus, lblMaritalStatus, errors);
        });
        txtInstruction.setOnAction(e -> {
            txtOcupation.requestFocus();
            Validations.validateComboBox(txtInstruction, lblInstruction, errors);
        });
        txtOcupation.setOnAction(e -> {
            txtHealthInsurance.requestFocus();
            Validations.validateWords(txtOcupation, lblOcupation, errors);
        });
        txtHealthInsurance.setOnAction(e -> {
            txtNamePeople.requestFocus();
            Validations.validateComboBox(txtHealthInsurance, lblHealthAssurance, errors);
        });
        txtNamePeople.setOnAction(e -> {
            txtRelationship.requestFocus();
            Validations.validateCompleteName(txtNamePeople, lblNamePeople, errors);
        });
        txtRelationship.setOnAction(e -> {
            txtAddressPeople.requestFocus();
            Validations.validateOneWord(txtRelationship, lblRelationship, errors);
        });
        txtAddressPeople.setOnAction(e -> {
            txtPhonePeople.requestFocus();
            Validations.validateNulls(txtAddressPeople, lblAdressPeople, errors);
        });
        txtPhonePeople.setOnAction(e -> {
            txtMailPeople.requestFocus();
            Validations.validatePhone(txtPhonePeople, lblPhonePeople, errors);
        });
        txtMailPeople.setOnAction(e -> {
            txtArrivalMethod.requestFocus();
            Validations.validateEmail(txtMailPeople, lblMailPeople, errors);
        });
        txtArrivalMethod.setOnAction(e -> {
            txtFontInformation.requestFocus();
            Validations.validateComboBox(txtArrivalMethod, lblArrivalMethod, errors);
        });
        txtFontInformation.setOnAction(e -> {
            txtInstitutionOrPeople.requestFocus();
            Validations.validateComboBox(txtFontInformation, lblInformationSource, errors);
        });
        txtInstitutionOrPeople.setOnAction(e -> {
            txtInstitutionOrPeoplePhone.requestFocus();
            Validations.validateNulls(txtInstitutionOrPeople, lblInstitutionOrPeople, errors);
        });
        txtInstitutionOrPeoplePhone.setOnAction(e -> {
            txtInstitutionOrPeople.requestFocus();
            Validations.validatePhoneV2(txtInstitutionOrPeoplePhone, lblInstitutionPhone, errors);
        });
    }

    @FXML
    void cancelRegistr(ActionEvent event) {
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeaderText("IMPORTANTE!");
        alert.setContentText("¿Está seguro de cancelar el registro?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

        }
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
        Message message = new Message();
        if (errors.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setHeaderText("IMPORTANTE!");
            alert.setContentText("¿Está seguro de guardar el registro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Query.queryRegisterPacient(txtFirstSurname,  txtLastSurname,  txtNames,  txtNationality,  txtIdentityCard,  txtBirthDate,
                         txtAddress,  txtCanton,  txtProvince,  txtPhone,  txtMail,  txtDateAttention,  txtHourAttention,
                         txtAge, txtSex,  txtCivilStatus, txtInstruction, txtOcupation,  txtHealthInsurance,
                         txtNamePeople,  txtRelationship,  txtAddressPeople,  txtPhonePeople,  txtMailPeople,txtArrivalMethod,
                         txtFontInformation,  txtInstitutionOrPeople,  txtInstitutionOrPeoplePhone);
                txtFirstSurname.setText(null);
                txtLastSurname.setText(null);
                txtNames.setText(null);
                txtNationality.setText(null);
                txtIdentityCard.setText(null);
                txtBirthDate.setValue(null);
                txtAddress.setText(null);
                txtCanton.setText(null);
                txtProvince.setText(null);
                txtPhone.setText(null);
                txtMail.setText(null);
                txtDateAttention.setValue(null);
                txtHourAttention.setText(null);
                txtAge.setText(null);
                txtSex.setValue(null);
                txtCivilStatus.setValue(null);
                txtInstruction.setValue(null);
                txtOcupation.setText(null);
                txtHealthInsurance.setValue(null);
                txtNamePeople.setText(null);
                txtRelationship.setText(null);
                txtAddressPeople.setText(null);
                txtPhonePeople.setText(null);
                txtMailPeople.setText(null);
                txtArrivalMethod.setValue(null);
                txtFontInformation.setValue(null);
                txtInstitutionOrPeople.setText(null);
                txtInstitutionOrPeoplePhone.setText(null);
                message.setInformation("El Paciente se ha registrado con éxito");
            } else{
                message.setInformation("No se guardó el registro del Paciente");
            }
        }else {
            message.setWarning("Ingrese la información correcta");
        }
    }
    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateOneWord(txtFirstSurname, lblFirstSurname, errors);
        Validations.validateOneWord(txtLastSurname, lblLastSurname, errors);
        Validations.validateTwoWords(txtNames, lblNamesE, errors);
        Validations.validateOneWord(txtNationality, lblNationality, errors);
        Validations.validateIdentityCard(txtIdentityCard, lblIdentityCard, errors);
        Validations.validateDateV2(txtBirthDate, lblBirthDate, errors);
        Validations.validateNulls(txtAddress, lblAdress, errors);
        Validations.validateWords(txtCanton, lblCanton, errors);
        Validations.validateWords(txtProvince, lblProvince, errors);
        Validations.validatePhone(txtPhone, lblPhoneNumber, errors);
        Validations.validateEmail(txtMail, lblMail, errors);
        Validations.validateDateV2(txtDateAttention, lblDateAttention, errors);
        Validations.validateHour(txtHourAttention, lblHourAttention, errors);
        Validations.validateAge(txtAge, lblAge, errors);
        Validations.validateComboBox(txtSex, lblSex, errors);
        Validations.validateComboBox(txtCivilStatus, lblMaritalStatus, errors);
        Validations.validateComboBox(txtInstruction, lblInstruction, errors);
        Validations.validateWords(txtOcupation, lblOcupation, errors);
        Validations.validateComboBox(txtHealthInsurance, lblHealthAssurance, errors);
        Validations.validateCompleteName(txtNamePeople, lblNamePeople, errors);
        Validations.validateOneWord(txtRelationship, lblRelationship, errors);
        Validations.validateNulls(txtAddressPeople, lblAdressPeople, errors);
        Validations.validatePhone(txtPhonePeople, lblPhonePeople, errors);
        Validations.validateEmail(txtMailPeople, lblMailPeople, errors);
        Validations.validateComboBox(txtArrivalMethod, lblArrivalMethod, errors);
        Validations.validateComboBox(txtFontInformation, lblInformationSource, errors);
        Validations.validateNulls(txtInstitutionOrPeople, lblInstitutionOrPeople, errors);
        Validations.validatePhoneV2(txtInstitutionOrPeoplePhone, lblInstitutionPhone, errors);
        return errors;
    }

}
