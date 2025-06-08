package com.prototype.cm2a.controllers.history;

import com.prototype.cm2a.controllers.medicine.MedicamentSearchController;
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

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeMedicalAttentionUpdateController {

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
    private MenuItem btnSearchTypeMedicalAttention;

    @FXML
    private HBox hBoxRectangle;

    @FXML
    private Label lblAttInformation;

    @FXML
    private Label lblAttName;

    @FXML
    private Label lblAttePrice;

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
    private TextField txtAge;

    @FXML
    private TextField txtAttCode;

    @FXML
    private TextField txtAttName;

    @FXML
    private TextField txtAttPrice;

    @FXML
    private TextField txtAtteInformation;

    @FXML
    private TextField txtIce;

    @FXML
    private HBox txtIceCode;

    @FXML
    private TextField txtIva;

    @FXML
    private TextField txtTurism;

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

    int typeCode;

    public void setDetailsType(int code){
        typeCode = code;
        String sql = "SELECT id, name, aditionalInformation, unitPrice FROM type_attention WHERE id = ?";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                txtAttCode.setText(String.valueOf(code));
                txtAttName.setText(resultSet.getString("name"));
                txtAtteInformation.setText(resultSet.getString("aditionalInformation"));
                txtAttPrice.setText(resultSet.getString("unitPrice"));
            }
        } catch (SQLException e) {
            System.out.println("Error in setDetailsType : " + e.getMessage());
            e.printStackTrace();
        }
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
    }

    @FXML
    void cancelRegister(ActionEvent event) {
        setDetailsType(typeCode);
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
            System.out.println("Error in Log out : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/type-medical-attention-search.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TypeMedicalAttentionSearchController typeMedicalAttentionSearchController = loader.getController();
            typeMedicalAttentionSearchController.setDataAutentication(userAutenticator);
            Stage currentStage = (Stage) btnLogOut.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error openDashboardHome - TypeMedicalAttentionDetailsController : " + e.getMessage());
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
        String insertType = "UPDATE type_attention SET name = ?, aditionalInformation = ?, unitPrice = ? WHERE id = ?";
        String selectType = "SELECT name, aditionalInformation, unitPrice FROM type_attention WHERE id = ?";
        Connection connection = DBConnection.connection();
        Alert alert;
        if (errors.isEmpty()){
            try {
                PreparedStatement prepareSelect = connection.prepareStatement(selectType);
                prepareSelect.setInt(1, typeCode);
                ResultSet resultSetSelect = prepareSelect.executeQuery();
                String currentName = null, currentAditional = null;
                double currentUnitPrice = 0;
                if (resultSetSelect.next()) {
                    currentName = resultSetSelect.getString("name");
                    currentAditional = resultSetSelect.getString("aditionalInformation");
                    currentUnitPrice = resultSetSelect.getDouble("unitPrice");
                }
                boolean hasChanges = !txtAttName.getText().equals(currentName)
                        || !txtAtteInformation.getText().equals(currentAditional)
                        || Double.parseDouble(txtAttPrice.getText()) != currentUnitPrice;
                if (hasChanges){
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("MENSAJE DE CONFIRMACIÓN");
                    alert.setHeaderText(null);
                    alert.setContentText("¿Estás seguro de guardar los nuevos datos del tipo de Atención Médica?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get().equals(ButtonType.OK)){
                        PreparedStatement preparedStatement = connection.prepareStatement(insertType);
                        preparedStatement.setString(1, txtAttName.getText());
                        preparedStatement.setString(2, txtAtteInformation.getText());
                        preparedStatement.setDouble(3, Double.parseDouble(txtAttPrice.getText()));
                        preparedStatement.setInt(4, typeCode);
                        preparedStatement.executeUpdate();
                        setDetailsType(typeCode);
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("¡INFORMACIÓN!");
                        alert.setHeaderText(null);
                        alert.setContentText("Atención Médica actualizada con éxito");
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
                System.out.println("Error in updateTypeAttention : " + e.getMessage());
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

    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateNulls(txtAttName, lblAttName, errors);
        Validations.validateNulls(txtAtteInformation, lblAttInformation, errors);
        Validations.validatePrice(txtAttPrice, lblAttePrice, errors);
        return errors;
    }
}
