package com.prototype.cm2a.controllers.principalwindows;

import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.User;
import com.prototype.cm2a.utils.Message;
import com.prototype.cm2a.utils.Validations;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogController {

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnforgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    public void initialize() {
        txtUser.setOnAction(e -> txtPassword.requestFocus());
        txtPassword.setOnAction(e -> btnLogIn.fire());
    }

    @FXML
    void createAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/register.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnRegister.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error creating account" + e.getMessage());
            e.printStackTrace();
        }
    }

    Message alert = new Message();
    int attempts = 0;
    @FXML
    void logIn(ActionEvent event) {
        String query = "SELECT username, password from users WHERE idNumber = ?";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, txtUser.getText());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String userDB = rs.getString("username");
                String passwordDB = rs.getString("password");
                //String hashedPasswordEntered = Validations.hashPassword(txtPassword.getText());
                String hashedPasswordEntered = passwordDB;
                User userAuntenticator = new User();
                if (userDB.equalsIgnoreCase(txtUser.getText()) && passwordDB.equalsIgnoreCase(hashedPasswordEntered)) {
                    String queryLog = "SELECT names, surnames, profession, rol from users WHERE idNumber = ?";
                    try (Connection conn = DBConnection.connection();
                         PreparedStatement prepare = conn.prepareStatement(queryLog)) {
                        prepare.setString(1, userDB);
                        ResultSet resultSet = prepare.executeQuery();
                        if (resultSet.next()) {
                            String namesDB = resultSet.getString("names");
                            String surnameDB = resultSet.getString("surnames");
                            String professionDB = resultSet.getString("profession");
                            String rolDB = resultSet.getString("rol");
                            userAuntenticator.setNames(namesDB);
                            userAuntenticator.setSurnames(surnameDB);
                            userAuntenticator.setProfession(professionDB);
                            userAuntenticator.setRole(rolDB);
                            try{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/principalwindows/dashboard.fxml"));
                                Parent root = loader.load();
                                DashboardController dashboardController = loader.getController();
                                dashboardController.setDataAutentication(userAuntenticator);
                                Scene scene = new Scene(root);
                                Stage currentStage = (Stage) btnLogIn.getScene().getWindow();
                                currentStage.setScene(scene);
                                currentStage.show();
                            }catch (IOException e){
                                System.out.println("Error creating account : " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }catch (SQLException a) {
                        System.out.println("Error creating account : " + a.getMessage());
                        a.printStackTrace();
                    }
                } else {
                    attempts++;
                    txtPassword.clear();
                    txtUser.clear();
                    if (attempts == 1 || attempts == 2) {
                        alert.setError("CREDENCIALES INCORRECTAS");
                        txtUser.requestFocus();
                    }
                    if (attempts == 3) {
                        alert.setError("CREDENCIALES INCORRECTAS. INTENTE DE NUEVO MAS TARDE");
                        txtUser.setDisable(true);
                        txtPassword.setDisable(true);
                        btnLogIn.setDisable(true);
                        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                        scheduler.schedule(() -> {
                            Platform.runLater(() -> txtUser.setDisable(false));
                            Platform.runLater(() -> txtPassword.setDisable(false));
                            Platform.runLater(() -> btnLogIn.setDisable(false));
                            txtUser.requestFocus();
                        }, 10, TimeUnit.SECONDS);
                        attempts = 0;
                    }
                }
            }
        }catch (SQLException a) {
            System.out.println("Error creating account : " + a.getMessage());
            a.printStackTrace();
        }
    }

    @FXML
    void recoverPassword(ActionEvent event) {

    }

}
