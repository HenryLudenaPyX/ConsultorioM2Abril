package com.prototype.cm2a.controllers.user;

import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class UserDetailsController {

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
    private TextField txtAdrress;

    @FXML
    private TextField txtExperience;

    @FXML
    private TextField txtGender;

    @FXML
    private HBox txtIceCode;

    @FXML
    private TextField txtIdentityCardUser;

    @FXML
    private TextField txtLastnamesUser;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNamesUser;

    @FXML
    private TextField txtPermissionRol;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProfession;

    @FXML
    private TextField txtUsername;

    public void setDetails(String identityNumber) {
        String query = "SELECT * FROM users WHERE idNumber = ?";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            if (con != null) {
                pstmt.setString(1, identityNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        txtIdentityCardUser.setText(rs.getString("idNumber"));
                        txtNamesUser.setText(rs.getString("names"));
                        txtLastnamesUser.setText(rs.getString("surnames"));
                        txtGender.setText(rs.getString("gender"));
                        txtPhone.setText(rs.getString("phone"));
                        txtMail.setText(rs.getString("email"));
                        txtAdrress.setText(rs.getString("address"));
                        txtProfession.setText(rs.getString("profession"));
                        txtExperience.setText(rs.getString("experienceYear"));
                        txtUsername.setText(rs.getString("username"));
                        txtPermissionRol.setText(rs.getString("rol"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in setDetailsUpdate: " + e.getMessage());
        }
    }

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
    void logOut(ActionEvent event) {

    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/user/user-search.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) menuModules.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openDashboardHome - UserDetailsController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openGenerateBilling(ActionEvent event) {

    }

    @FXML
    void openQueryBilling(ActionEvent event) {

    }

    @FXML
    void openQueryClinicHistory(ActionEvent event) {

    }

    @FXML
    void openQueryMedicament(ActionEvent event) {

    }

    @FXML
    void openQueryPacient(ActionEvent event) {

    }

    @FXML
    void openQueryParameter(ActionEvent event) {

    }

    @FXML
    void openQueryUser(ActionEvent event) {

    }

    @FXML
    void openRegisterClinicHistory(ActionEvent event) {

    }

    @FXML
    void openRegisterMedicalAttention(ActionEvent event) {

    }

    @FXML
    void openRegisterMedicament(ActionEvent event) {

    }

    @FXML
    void openRegisterPacient(ActionEvent event) {

    }

    @FXML
    void openRegisterParameter(ActionEvent event) {

    }

    @FXML
    void openRegisterTypeMedicalAttention(ActionEvent event) {

    }

    @FXML
    void openRegisterUser(ActionEvent event) {

    }

    @FXML
    void openSearchTypeMedicalAttention(ActionEvent event) {

    }
}
