package com.prototype.cm2a.controllers.pacient;

import com.prototype.cm2a.components.PacientCard;
import com.prototype.cm2a.database.DBConnection;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacientUpdateController {

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
    private TextField txtBirthDate;

    @FXML
    private TextField txtCanton;

    @FXML
    private ComboBox<String> txtCivilStatus;
    private String[] civilStatus = {"Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a", "Unión libre"};

    @FXML
    private TextField txtDateAttention;

    @FXML
    private TextField txtFirstSurname;

    @FXML
    private ComboBox<String> txtFontInformation;
    private String[] fontInformation = {"Directo", "Indirecto"};

    @FXML
    private ComboBox<String> txtHealthInsurance;
    private String[] healthInsurance = {"IESS","ISSFA", "ISSPOL", "MSP", "SPPAT", "Privado", "Otro"};

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
    private String[] instruction = {"Sin instruccion", "Basica", "Bachillerato", "Superior", "Especial"};

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

    String identityNumberQuery;

    List<String> errors = new ArrayList<>();

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

    public void setDetails(String identityNumber) {
        identityNumberQuery = identityNumber;
        String query = "SELECT * FROM pacient WHERE idNumber = ?";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            if (con != null) {
                pstmt.setString(1, identityNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        txtFirstSurname.setText(rs.getString("paternalLastname"));
                        txtLastSurname.setText(rs.getString("maternalLastname"));
                        txtNames.setText(rs.getString("names"));
                        txtNationality.setText(rs.getString("nationality"));
                        txtIdentityCard.setText(rs.getString("idNumber"));
                        txtBirthDate.setText(rs.getString("birthDate"));
                        txtAddress.setText(rs.getString("address"));
                        txtCanton.setText(rs.getString("canton"));
                        txtProvince.setText(rs.getString("province"));
                        txtPhone.setText(rs.getString("phoneNumber"));
                        txtMail.setText(rs.getString("email"));
                        txtDateAttention.setText(rs.getString("attentionDate"));
                        txtHourAttention.setText(rs.getString("attentionTime"));
                        txtAge.setText(rs.getString("age"));
                        txtSex.setValue(rs.getString("gender"));
                        txtCivilStatus.setValue(rs.getString("maritalStatus"));
                        txtInstruction.setValue(rs.getString("education"));
                        txtOcupation.setText(rs.getString("occupation"));
                        txtHealthInsurance.setValue(rs.getString("healthInsurance"));
                        txtNamePeople.setText(rs.getString("notificationPersonName"));
                        txtRelationship.setText(rs.getString("relationshipPerson"));
                        txtAddressPeople.setText(rs.getString("notificationPersonAddress"));
                        txtPhonePeople.setText(rs.getString("notificationPersonPhoneNumber"));
                        txtMailPeople.setText(rs.getString("notificationPersonEmail"));
                        txtArrivalMethod.setValue(rs.getString("arrivalMethod"));
                        txtFontInformation.setValue(rs.getString("informationSource"));
                        txtInstitutionOrPeople.setText(rs.getString("institutionOrPersonDeliveringPatient"));
                        txtInstitutionOrPeoplePhone.setText(rs.getString("deliveringPersonPhoneNumber"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in setDetailsUpdate: " + e.getMessage());
        }
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
        txtAddress.setOnAction(e -> {
            txtPhone.requestFocus();
            Validations.validateNulls(txtAddress, lblAdress, errors);
        });
        txtPhone.setOnAction(e -> {
            txtMail.requestFocus();
            Validations.validatePhone(txtPhone, lblPhoneNumber, errors);
        });
        txtMail.setOnAction(e -> {
            txtNamePeople.requestFocus();
            Validations.validateEmail(txtMail, lblMail, errors);
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
            txtPhonePeople.requestFocus();
            Validations.validateEmail(txtMailPeople, lblMailPeople, errors);
        });
    }

    @FXML
    void cancelRegistr(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeaderText("IMPORTANTE!");
        alert.setContentText("¿Está seguro de cancelar el registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/pacient/pacient-update.fxml"));
                Parent root = loader.load();
                PacientUpdateController pacientUpdateController = loader.getController();
                pacientUpdateController.setDataAutentication(userAutenticator);
                Scene scene = new Scene(root);
                Stage currentStage = (Stage) btnCancel.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }catch (IOException e){
                System.out.println("Error in cancelRegister - PacientUpdateController : " + e.getMessage());
            }

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
            System.out.println("Error in Log out : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
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
            System.out.println("Error in openQueryPacient - DashboardController : " + e.getMessage());
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
        List<String> errors = validateInputs();
        Message message = new Message();
        if (errors.isEmpty()) {
            if(Query.compareChanges(identityNumberQuery, txtAddress, txtPhone, txtMail, txtNamePeople, txtRelationship, txtAddressPeople, txtPhonePeople, txtMailPeople)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setHeaderText("IMPORTANTE!");
                alert.setContentText("¿Está seguro de guardar los cambios?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Query.queryUpdatePacient(identityNumberQuery, txtAddress, txtPhone, txtMail, txtNamePeople, txtRelationship, txtAddressPeople, txtPhonePeople, txtMailPeople);
                    message.setInformation("Los cambios se han guardado con éxito");
                }else{
                    message.setError("No se guardó el registro del Paciente");
                }
            }else {
                message.setError("No se han registrado cambios");
            }
        }else {
            message.setError("Ingrese la información correcta");
        }
    }

    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateNulls(txtAddress, lblAdress, errors);
        Validations.validatePhone(txtPhone, lblPhoneNumber, errors);
        Validations.validateEmail(txtMail, lblMail, errors);
        Validations.validateCompleteName(txtNamePeople, lblNamePeople, errors);
        Validations.validateOneWord(txtRelationship, lblRelationship, errors);
        Validations.validateNulls(txtAddressPeople, lblAdressPeople, errors);
        Validations.validatePhone(txtPhonePeople, lblPhonePeople, errors);
        Validations.validateEmail(txtMailPeople, lblMailPeople, errors);
        return errors;
    }
}
