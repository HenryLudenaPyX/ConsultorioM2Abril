package com.prototype.cm2a.controllers.medicine;

import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
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

public class MedicamentUpdateController {

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
    private Label lblFabricationDate;

    @FXML
    private Label lblHour;

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

    List<String> errors = new ArrayList<>();

    int medicamentCode;

    public void setDataAutentication(User user){
        userAutenticator = user;
        userRolAutentication = user.getRole();
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblPosition.setText(user.getProfession());
        lblPermission.setText(user.getRole());
    }

    public void setDetailsMedicament(int id){
        medicamentCode = id;
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
        setDetailsMedicament(medicamentCode);
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("¡INFORMACIÓN!");
        alert.setHeaderText(null);
        alert.setContentText("Se han descartado los cambios");
        alert.showAndWait();
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
            System.out.println("Error in logOut - MedicamentUpdateController : " + e.getMessage());
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
            System.out.println("Error in openDashboardHome - MedicamentUpdateController : " + e.getMessage());
            e.printStackTrace();
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
        String insertMedicament = "UPDATE medicament SET name = ?, aditional = ?, providerPrice = ?, unitPrice = ?, stock = ?, fabricationDate = ?, expirationDate = ? WHERE id = ?";
        String selectMedicament = "SELECT name, aditional, providerPrice, unitPrice, stock, fabricationDate, expirationDate FROM medicament WHERE id = ?";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (errors.isEmpty()){
            try {
                PreparedStatement prepareSelect = connection.prepareStatement(selectMedicament);
                prepareSelect.setInt(1, medicamentCode);
                ResultSet resultSetSelect = prepareSelect.executeQuery();
                String currentName = null, currentAditional = null, currentFabricationDate = null, currentExpirationDate = null;
                double currentProviderPrice = 0, currentUnitPrice = 0;
                int currentStock = 0;
                if (resultSetSelect.next()) {
                    currentName = resultSetSelect.getString("name");
                    currentAditional = resultSetSelect.getString("aditional");
                    currentProviderPrice = resultSetSelect.getDouble("providerPrice");
                    currentUnitPrice = resultSetSelect.getDouble("unitPrice");
                    currentStock = resultSetSelect.getInt("stock");
                    currentFabricationDate = resultSetSelect.getString("fabricationDate");
                    currentExpirationDate = resultSetSelect.getString("expirationDate");
                }
                boolean hasChanges = !txtMedicamentName.getText().equals(currentName)
                        || !txtAdicionalInformation.getText().equals(currentAditional)
                        || Double.parseDouble(txtPrice.getText()) != currentProviderPrice
                        || Double.parseDouble(txtUnitPrice.getText()) != currentUnitPrice
                        || Integer.parseInt(txtStock.getText()) != currentStock
                        || !txtFabricationDate.getValue().toString().equals(currentFabricationDate)
                        || !txtExpirationDate.getValue().toString().equals(currentExpirationDate);
                if (hasChanges){
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("MENSAJE DE CONFIRMACIÓN");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Estás seguro de guardar los nuevos datos del medicamento?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)){
                        PreparedStatement preparedStatement = connection.prepareStatement(insertMedicament);
                        preparedStatement.setString(1, txtMedicamentName.getText());
                        preparedStatement.setString(2, txtAdicionalInformation.getText());
                        preparedStatement.setDouble(3, Double.parseDouble(txtPrice.getText()));
                        preparedStatement.setDouble(4, Double.parseDouble(txtUnitPrice.getText()));
                        preparedStatement.setInt(5, Integer.parseInt(txtStock.getText()));
                        preparedStatement.setDate(6, Date.valueOf(txtFabricationDate.getValue()));
                        preparedStatement.setDate(7, Date.valueOf(txtExpirationDate.getValue()));
                        preparedStatement.setInt(8, medicamentCode);
                        preparedStatement.executeUpdate();
                        setDetailsMedicament(medicamentCode);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("¡INFORMACIÓN!");
                        alert.setHeaderText(null);
                        alert.setContentText("Medicamento actualizado con éxito");
                        alert.showAndWait();
                    }
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("¡INFORMACIÓN!");
                    alert.setHeaderText(null);
                    alert.setContentText("No se han visto cambios");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                System.out.println("Error in updateMedicament : " + e.getMessage());
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
    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
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
