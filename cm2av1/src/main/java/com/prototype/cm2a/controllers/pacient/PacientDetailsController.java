package com.prototype.cm2a.controllers.pacient;

import com.prototype.cm2a.components.PacientCard;
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Functions;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacientDetailsController {

    @FXML
    private Button btnBackHome;

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
    private HBox hBoxRectangle;

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
    private TextField txtAddress;

    @FXML
    private TextField txtAddressPeople;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtArrivalMethod;

    @FXML
    private TextField txtBirthDate;

    @FXML
    private TextField txtCanton;

    @FXML
    private TextField txtCivilStatus;

    @FXML
    private TextField txtDateAttention;

    @FXML
    private TextField txtFirstSurname;

    @FXML
    private TextField txtFontInformation;

    @FXML
    private TextField txtHealthInsurance;

    @FXML
    private TextField txtHourAttention;

    @FXML
    private TextField txtIdentityCard;

    @FXML
    private TextField txtInstitutionOrPeople;

    @FXML
    private TextField txtInstitutionOrPeoplePhone;

    @FXML
    private TextField txtInstruction;

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

    @FXML
    private TextField txtSex;

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
                        txtSex.setText(rs.getString("gender"));
                        txtCivilStatus.setText(rs.getString("maritalStatus"));
                        txtInstruction.setText(rs.getString("education"));
                        txtOcupation.setText(rs.getString("occupation"));
                        txtHealthInsurance.setText(rs.getString("healthInsurance"));
                        txtNamePeople.setText(rs.getString("notificationPersonName"));
                        txtRelationship.setText(rs.getString("relationshipPerson"));
                        txtAddressPeople.setText(rs.getString("notificationPersonAddress"));
                        txtPhonePeople.setText(rs.getString("notificationPersonPhoneNumber"));
                        txtMailPeople.setText(rs.getString("notificationPersonEmail"));
                        txtArrivalMethod.setText(rs.getString("arrivalMethod"));
                        txtFontInformation.setText(rs.getString("informationSource"));
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
    public void initialize() {
        Functions.updateDateTime(lblHour);
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
}
