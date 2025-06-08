package com.prototype.cm2a.components;

import com.prototype.cm2a.controllers.user.UserDetailsController;
import com.prototype.cm2a.controllers.user.UserUpdateController;
import com.prototype.cm2a.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserCard {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnViewMore;

    @FXML
    private Pane card;

    @FXML
    private Label lblCI;

    @FXML
    private Label lblNames;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblRol;

    @FXML
    private Label lblSurnames;

    private User user;

    public void setDataCard(User user) {
        this.user = user;
        lblCI.setText(user.getIdNumber());
        lblNames.setText(user.getNames());
        lblSurnames.setText(user.getSurnames());
        lblRol.setText(user.getRole());
        lblPhone.setText(user.getPhone());
    }

    @FXML
    void openEdit(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/user/user-update.fxml"));
            Parent root = loader.load();
            UserUpdateController userUpdateController = loader.getController();
            userUpdateController.setDetails(user.getIdNumber());
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnEdit.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openEdit - UserCard : " + e.getMessage());
        }
    }

    @FXML
    void openViewMore(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/user/user-details.fxml"));
            Parent root = loader.load();
            UserDetailsController userDetailsController = loader.getController();
            userDetailsController.setDetails(user.getIdNumber());
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) btnViewMore.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openViewMore - UserCard : " + e.getMessage());
        }
    }

}
