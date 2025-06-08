package com.prototype.cm2a.controllers.history;

import com.prototype.cm2a.components.AdmissionDetailsCard;
import com.prototype.cm2a.components.ClinicHistoryDetailsCard;
import com.prototype.cm2a.components.MedicalAttentionCard;
import com.prototype.cm2a.controllers.pacient.PacientRegisterController;
import com.prototype.cm2a.controllers.pacient.PacientSearchController;
import com.prototype.cm2a.controllers.user.UserRegisterController;
import com.prototype.cm2a.controllers.user.UserSearchController;
import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.MedicalAttention;
import com.prototype.cm2a.models.Pacient;
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
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicHistoryDetailsController {

    @FXML
    private MenuItem btnGenerateBilling;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnBackHome;

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
    private Pagination pagination;

    @FXML
    private Pane panePagination;

    @FXML
    private Label titleModule;

    @FXML
    private Label titleModule1;

    private Parent pacientDetailsRoot;
    private Parent clinicHistoryDetailsRoot;
    private String identityNumberCard;
    private String codeHistory;

    public void setDetails(String identityNumber, String code) {
        codeHistory = code;
        identityNumberCard = identityNumber;
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
    private int totalRecords;
    private List<MedicalAttention> medicalAttentionCardDataList = new ArrayList<>();
    private List<Pane> medicalAttentionPanes = new ArrayList<>();

    @FXML
    public void initialize(String id) {
        Functions.updateDateTime(lblHour);
        medicalAttentionPanes = new ArrayList<>();
        Cards = Cards();
        for (MedicalAttention medicalAttention : Cards) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/components/medical-attention-details-card.fxml"));
            try {
                Pane cardPane = loader.load();
                MedicalAttentionCard medicalAttentionCard = loader.getController();
                medicalAttentionCard.setDetails(medicalAttention);
                medicalAttentionPanes.add(cardPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loadMedicalAttentionCards(id);
        pagination.setPageFactory(pageIndex -> {
            if (pageIndex < 2) {
                switch (pageIndex) {
                    case 0:
                        if (pacientDetailsRoot == null) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/components/admission-details-card.fxml"));
                            try {
                                pacientDetailsRoot = loader.load();
                                AdmissionDetailsCard admissionDetailsCard = loader.getController();
                                admissionDetailsCard.setDetails(identityNumberCard);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        return pacientDetailsRoot;
                    case 1:
                        if (clinicHistoryDetailsRoot == null) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/components/clinic-history-details-card.fxml"));
                            try {
                                clinicHistoryDetailsRoot = loader.load();
                                ClinicHistoryDetailsCard clinicHistoryDetailsCard = loader.getController();
                                clinicHistoryDetailsCard.setDetails(identityNumberCard, codeHistory);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        return clinicHistoryDetailsRoot;
                    default:
                        return new Pane();
                }
            } else {
                int cardIndex = pageIndex - 2;
                if (cardIndex >= 0 && cardIndex < medicalAttentionPanes.size()) {
                    return medicalAttentionPanes.get(cardIndex);
                } else {
                    return new Pane();
                }
            }
        });
    }

    private void loadMedicalAttentionCards(String id) {
        String select = "SELECT COUNT(*) FROM madical_attention WHERE id_pacient = ?";
        try (Connection connection = DBConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRecords = resultSet.getInt(1);
                System.out.println(identityNumberCard);
                System.out.println(totalRecords);
            }
        } catch (SQLException e) {
            System.out.println("Error loading medical attention card count: " + e.getMessage());
            e.printStackTrace();
        }
        int totalPages = totalRecords + 2;
        pagination.setPageCount(totalPages);
    }
    private List<MedicalAttention> Cards;
    private List<MedicalAttention> Cards(){
        List<MedicalAttention> medicalAttentionsLis = new ArrayList<>();
        String select = "SELECT id_pacient, date_attention, hour_attention, evolution, prescription, medication, name_medic FROM madical_attention WHERE id_pacient = ?";
        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, identityNumberCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MedicalAttention medicalAttention = new MedicalAttention();
                medicalAttention.setId(resultSet.getString("id_pacient"));
                medicalAttention.setDate(resultSet.getString("date_attention"));
                medicalAttention.setTime(resultSet.getString("hour_attention"));
                medicalAttention.setEvolution(resultSet.getString("evolution"));
                medicalAttention.setPreinscription(resultSet.getString("prescription"));
                medicalAttention.setMedication(resultSet.getString("medication"));
                medicalAttention.setMedic(resultSet.getString("name_medic"));
                medicalAttentionsLis.add(medicalAttention);
            }
        }catch (SQLException e) {
            System.out.println("Error in MedicalAttentionCard: " + e.getMessage());
            e.printStackTrace();
        }
        return medicalAttentionsLis;
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
            System.out.println("Error in logOut - ClinicHistoryDetailsController : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void openDashboardHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/prototype/cm2a/secondarywindows/attention/clinic-history-search.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ClinicHistorySearchController clinicHistorySearchController = loader.getController();
            clinicHistorySearchController.setDataAutentication(userAutenticator);
            Stage currentStage = (Stage) btnBackHome.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        }catch (IOException e){
            System.out.println("Error in openDashboardHome - ClinicHistoryDetailsController : " + e.getMessage());
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
