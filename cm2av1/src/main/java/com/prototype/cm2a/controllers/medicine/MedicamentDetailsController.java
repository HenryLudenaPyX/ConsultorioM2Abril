package com.prototype.cm2a.controllers.medicine;

import com.prototype.cm2a.controllers.principalwindows.DashboardController;
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedicamentDetailsController {

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
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private MenuItem btnRegisterUser;

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

    User userAutenticator = new User();

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

    public void setDetailsMedicament(int id){
        String sql = "SELECT id, name, aditional, providerPrice, unitPrice, stock, fabricationDate, expirationDate FROM medicament WHERE id = ?";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                txtMedicamentCode.setText(String.valueOf(id));
                txtMedicamentName.setText(resultSet.getString("name"));
                txtAdicionalInformation.setText(resultSet.getString("aditional"));
                txtPrice.setText(resultSet.getString("providerPrice"));
                txtUnitPrice.setText(resultSet.getString("unitPrice"));
                txtStock.setText(resultSet.getString("stock"));
                Date sqlDateFabrication = resultSet.getDate("fabricationDate");
                LocalDate localDateFabrication = sqlDateFabrication.toLocalDate();
                txtFabricationDate.setValue(localDateFabrication);
                Date sqlDateExpiration = resultSet.getDate("expirationDate");
                LocalDate localDateExpiration = sqlDateExpiration.toLocalDate();
                txtExpirationDate.setValue(localDateExpiration);
            }
        } catch (SQLException e) {
            System.out.println("Error in setDetailsMedicament : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtFabricationDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date == null ? "" : date.format(dateFormatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return string == null || string.isEmpty() ? null : LocalDate.parse(string, dateFormatter);
            }
        });

        txtExpirationDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return date == null ? "" : date.format(dateFormatter);
            }

            @Override
            public LocalDate fromString(String string) {
                return string == null || string.isEmpty() ? null : LocalDate.parse(string, dateFormatter);
            }
        });
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
            System.out.println("Error in logOut - MedicamentDetailsController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/medicine/medicament-search.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            MedicamentSearchController medicamentSearchController = loader.getController();
            medicamentSearchController.setDataAutentication(userAutenticator);
            Stage currentStage = (Stage) btnBackHome.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openDashboardHome - MedicamentDetailsController : " + e.getMessage());
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
