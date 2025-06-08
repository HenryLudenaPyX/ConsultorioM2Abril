package com.prototype.cm2a.controllers.principalwindows;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterController {

    @FXML
    private Button btnBackLog;

    @FXML
    private Button btnRegisterUser;

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUser;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    List<String> errors = new ArrayList<>();

    @FXML
    public void initialize() {
        txtUser.setOnAction(e -> {
            txtPassword.requestFocus();
            Validations.validateNoExistenceIdentityCard(lblUser, txtUser, errors, "Users");
        });
        txtPassword.setOnAction(e -> {
            txtConfirmPassword.requestFocus();
            Validations.validatePassword(txtPassword, lblPassword, errors);
        });
        txtConfirmPassword.setOnAction(e -> {
            txtUser.requestFocus();
            Validations.validatePassword(txtConfirmPassword, lblConfirmPassword, errors);
            Validations.validateSimilarPasswords(lblPassword, txtPassword, lblConfirmPassword, txtConfirmPassword, errors);
        });
    }

    @FXML
    void openLog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/log.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnBackLog.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error creating account" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void registerUser(ActionEvent event) {
        List<String> errors = validateInputs();
        Message message = new Message();
        if (errors.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setHeaderText("IMPORTANTE!");
            alert.setContentText("¿Está seguro de guardar su registro?");
            Optional<ButtonType> result = alert.showAndWait();
            System.out.println("Hasta aqui: "+result.get());
            if (result.get() == ButtonType.OK){
                if(txtPassword.getText().equalsIgnoreCase(txtConfirmPassword.getText())){
                    String passwordHash = Validations.hashPassword(txtPassword.getText());
                    //System.out.println("Usuario: "+txtUser.getText() +"contrasena: "+txtPassword.getText());
                    Query.updatePassword(txtUser.getText(), passwordHash);
                    message.setInformation("El Usuario se ha registrado con éxito");
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/log.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage currentStage = (Stage) btnBackLog.getScene().getWindow();
                        currentStage.setScene(scene);
                        currentStage.show();
                    }catch (IOException e){
                        System.out.println("Error in back" + e.getMessage());
                        e.printStackTrace();
                    }
                }else{
                    message.setError("Las contraseñas no coinciden");
                }
            }else{
                message.setInformation("No se guardó el registro del Usuario");
            }
        }else {
            message.setWarning("Ingrese la información correcta");
        }
    }

    private List<String> validateInputs() {
        List<String> errors = new ArrayList<>();
        Validations.validateIdentityCard(txtUser, lblUser, errors);
        Validations.validatePassword(txtPassword, lblPassword, errors);
        Validations.validatePassword(txtConfirmPassword, lblConfirmPassword, errors);
        return errors;
    }

}
