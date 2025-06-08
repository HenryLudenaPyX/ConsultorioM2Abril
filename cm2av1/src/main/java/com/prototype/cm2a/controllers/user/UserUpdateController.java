package com.prototype.cm2a.controllers.user;

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

public class UserUpdateController {

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
    private Label lblExperience;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblHour;

    @FXML
    private Label lblMail;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblNamesUser;

    @FXML
    private Label lblPermission;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblProfession;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblSurnames;

    @FXML
    private Label lblSurnamesUser;

    @FXML
    private Label lbldentityNumber;

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
    private ComboBox<String> txtPermissionRol;
    private String[] role = {"Administrador", "Medicina", "Administración"};

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProfession;

    @FXML
    private TextField txtUsername;

    @FXML
    public void initialize() {
        Functions.updateDateTime(lblHour);
        txtPermissionRol.getItems().addAll(role);
    }

    private String identityNumberQuery;

    public void setDetails(String identityNumber) {
        identityNumberQuery = identityNumber;
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
                        txtPermissionRol.setValue(rs.getString("rol"));
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
    void cancelRegister(ActionEvent event) {

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
            System.out.println("Error in openDashboardHome - UserUpdateController : " + e.getMessage());
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

    @FXML
    void saveRegister(ActionEvent event) {
        List<String> errors = validateInputs();
        Message message = new Message();
        if (errors.isEmpty()) {
            if(Query.compareChangesUser(identityNumberQuery, txtPhone, txtMail, txtAdrress, txtExperience, txtPermissionRol)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setHeaderText("IMPORTANTE!");
                alert.setContentText("¿Está seguro de guardar los cambios?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Query.queryUpdateUser(identityNumberQuery, txtPhone, txtMail, txtAdrress, txtExperience, txtPermissionRol);
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
        Validations.validatePhone(txtPhone, lblPhone, errors);
        Validations.validateEmail(txtMail, lblMail, errors);
        return errors;
    }
}
