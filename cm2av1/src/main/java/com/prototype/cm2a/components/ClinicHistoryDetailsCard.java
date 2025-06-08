package com.prototype.cm2a.components;

import com.prototype.cm2a.database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;

public class ClinicHistoryDetailsCard {

    @FXML
    private DatePicker dateVitalSigns;

    @FXML
    private DatePicker dateVitalSigns1;

    @FXML
    private DatePicker dateVitalSigns2;

    @FXML
    private DatePicker dateVitalSigns3;

    @FXML
    private DatePicker dateVitalSigns4;

    @FXML
    private TextField pressureVitalSigns;

    @FXML
    private TextField pressureVitalSigns1;

    @FXML
    private TextField pressureVitalSigns2;

    @FXML
    private TextField pressureVitalSigns3;

    @FXML
    private TextField pressureVitalSigns4;

    @FXML
    private TextField pulseVitalSigns;

    @FXML
    private TextField pulseVitalSigns1;

    @FXML
    private TextField pulseVitalSigns2;

    @FXML
    private TextField pulseVitalSigns3;

    @FXML
    private TextField pulseVitalSigns4;

    @FXML
    private TextField temperatureVitalSigns;

    @FXML
    private TextField temperatureVitalSigns1;

    @FXML
    private TextField temperatureVitalSigns2;

    @FXML
    private TextField temperatureVitalSigns3;

    @FXML
    private TextField temperatureVitalSigns4;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private TextField txtCodeCie;

    @FXML
    private TextField txtCodeCie1;

    @FXML
    private TextField txtCodeCie2;

    @FXML
    private TextField txtCodeCie3;

    @FXML
    private TextField txtCodeClinicHistory;

    @FXML
    private TextArea txtCurentRevision;

    @FXML
    private DatePicker txtDateControl;

    @FXML
    private TextField txtDiagnosticName;

    @FXML
    private TextField txtDiagnosticName1;

    @FXML
    private TextField txtDiagnosticName2;

    @FXML
    private TextField txtDiagnosticName3;

    @FXML
    private TextArea txtDisease;

    @FXML
    private TextField txtEstablishment;

    @FXML
    private TextArea txtFamiliarAntecedents;

    @FXML
    private TextField txtHourControl;

    @FXML
    private TextField txtLastnamesPacient;

    @FXML
    private TextField txtNameDoctor;

    @FXML
    private TextField txtNamesPacient;

    @FXML
    private TextField txtNumberSheet;

    @FXML
    private ComboBox<String> txtOpcionPreDef;
    private String[] items = {"PRE", "DEF"};

    @FXML
    private ComboBox<String> txtOpcionPreDef1;

    @FXML
    private ComboBox<String> txtOpcionPreDef2;

    @FXML
    private ComboBox<String> txtOpcionPreDef3;

    @FXML
    private TextArea txtPersonalAntecedents;

    @FXML
    private TextArea txtPhysycExam;

    @FXML
    private TextArea txtPlans;

    @FXML
    private TextArea txtReasonConsultation;

    @FXML
    public void initialize() {
        txtOpcionPreDef.getItems().addAll(items);
        txtOpcionPreDef1.getItems().addAll(items);
        txtOpcionPreDef2.getItems().addAll(items);
        txtOpcionPreDef3.getItems().addAll(items);
    }

    public void setDetails(String idNumber, String code) {
        String selectHistory = "SELECT id, numberSheet, idNumber, consultReason, personalAntecedents, familyAntecedents, disease, currentRevision, physicExam, plansDiagnostic, controlDate, controlHour, nameMedic FROM clinic_history WHERE id = ?";
        String selectPacient = "SELECT pa.paternalLastname, pa.maternalLastname, pa.names FROM clinic_history AS ch INNER JOIN pacient AS pa ON ch.idNumber = pa.idNumber WHERE ch.idNumber = ?";
        String selectVitalsSigns = "SELECT line, numberSheet, date_attention, pressure, pulse, temperature FROM vital_signs WHERE id_clinic_history = ?";
        String selectDiagnostic = "SELECT line, numberSheet, description, codeCie, type_diagnostic FROM diagnostic WHERE id_clinic_history = ?";

        Connection connection = DBConnection.connection();
        try {
            PreparedStatement preparedStatementSelectHistory = connection.prepareStatement(selectHistory);
            preparedStatementSelectHistory.setString(1, code);
            ResultSet resultSetHistory = preparedStatementSelectHistory.executeQuery();

            PreparedStatement preparedStatementSelectDiagnostic = connection.prepareStatement(selectDiagnostic);
            preparedStatementSelectDiagnostic.setString(1, code);
            ResultSet resultSetDiagnostic = preparedStatementSelectDiagnostic.executeQuery();

            PreparedStatement preparedStatementSelectVitals = connection.prepareStatement(selectVitalsSigns);
            preparedStatementSelectVitals.setString(1, code);
            ResultSet resultSetVitals = preparedStatementSelectVitals.executeQuery();

            PreparedStatement preparedStatementSelectPacient = connection.prepareStatement(selectPacient);
            preparedStatementSelectPacient.setString(1, idNumber);
            ResultSet resultSetPacient = preparedStatementSelectPacient.executeQuery();

            // Process patient data
            if (resultSetPacient.next()) {
                txtNamesPacient.setText(resultSetPacient.getString("names"));
                txtLastnamesPacient.setText(resultSetPacient.getString("paternalLastname") + " " + resultSetPacient.getString("maternalLastname"));
            }

            // Process history data
            if (resultSetHistory.next()) {
                txtCodeClinicHistory.setText(code);
                txtNumberSheet.setText(resultSetHistory.getString("numberSheet"));
                txtCardNumber.setText(resultSetHistory.getString("idNumber"));
                txtEstablishment.setText("2 de Abril");
                txtReasonConsultation.setText(resultSetHistory.getString("consultReason"));
                txtPersonalAntecedents.setText(resultSetHistory.getString("personalAntecedents"));
                txtFamiliarAntecedents.setText(resultSetHistory.getString("familyAntecedents"));
                txtDisease.setText(resultSetHistory.getString("disease"));
                txtCurentRevision.setText(resultSetHistory.getString("currentRevision"));
                txtPhysycExam.setText(resultSetHistory.getString("physicExam"));
                txtPlans.setText(resultSetHistory.getString("plansDiagnostic"));
                Date sqlDate = resultSetHistory.getDate("controlDate");
                LocalDate localDate = sqlDate.toLocalDate();
                txtDateControl.setValue(localDate);
                txtHourControl.setText(resultSetHistory.getString("controlHour"));
                txtNameDoctor.setText(resultSetHistory.getString("nameMedic"));
            }

            // Process vital signs data
            while (resultSetVitals.next()) {
                LocalDate localDate = resultSetVitals.getDate("date_attention").toLocalDate();
                switch (resultSetVitals.getInt("line")) {
                    case 1:
                        dateVitalSigns.setValue(localDate);
                        pressureVitalSigns.setText(resultSetVitals.getString("pressure"));
                        pulseVitalSigns.setText(resultSetVitals.getString("pulse"));
                        temperatureVitalSigns.setText(resultSetVitals.getString("temperature"));
                        break;
                    case 2:
                        dateVitalSigns1.setValue(localDate);
                        pressureVitalSigns1.setText(resultSetVitals.getString("pressure"));
                        pulseVitalSigns1.setText(resultSetVitals.getString("pulse"));
                        temperatureVitalSigns1.setText(resultSetVitals.getString("temperature"));
                        break;
                    case 3:
                        dateVitalSigns2.setValue(localDate);
                        pressureVitalSigns2.setText(resultSetVitals.getString("pressure"));
                        pulseVitalSigns2.setText(resultSetVitals.getString("pulse"));
                        temperatureVitalSigns2.setText(resultSetVitals.getString("temperature"));
                        break;
                    case 4:
                        dateVitalSigns3.setValue(localDate);
                        pressureVitalSigns3.setText(resultSetVitals.getString("pressure"));
                        pulseVitalSigns3.setText(resultSetVitals.getString("pulse"));
                        temperatureVitalSigns3.setText(resultSetVitals.getString("temperature"));
                        break;
                    case 5:
                        dateVitalSigns4.setValue(localDate);
                        pressureVitalSigns4.setText(resultSetVitals.getString("pressure"));
                        pulseVitalSigns4.setText(resultSetVitals.getString("pulse"));
                        temperatureVitalSigns4.setText(resultSetVitals.getString("temperature"));
                        break;
                }
            }

            // Process diagnostic data
            while (resultSetDiagnostic.next()) {
                switch (resultSetDiagnostic.getInt("line")) {
                    case 1:
                        txtDiagnosticName.setText(resultSetDiagnostic.getString("description"));
                        txtCodeCie.setText(resultSetDiagnostic.getString("codeCie"));
                        txtOpcionPreDef.setValue(resultSetDiagnostic.getString("type_diagnostic"));
                        break;
                    case 2:
                        txtDiagnosticName1.setText(resultSetDiagnostic.getString("description"));
                        txtCodeCie1.setText(resultSetDiagnostic.getString("codeCie"));
                        txtOpcionPreDef1.setValue(resultSetDiagnostic.getString("type_diagnostic"));
                        break;
                    case 3:
                        txtDiagnosticName2.setText(resultSetDiagnostic.getString("description"));
                        txtCodeCie2.setText(resultSetDiagnostic.getString("codeCie"));
                        txtOpcionPreDef2.setValue(resultSetDiagnostic.getString("type_diagnostic"));
                        break;
                    case 4:
                        txtDiagnosticName3.setText(resultSetDiagnostic.getString("description"));
                        txtCodeCie3.setText(resultSetDiagnostic.getString("codeCie"));
                        txtOpcionPreDef3.setValue(resultSetDiagnostic.getString("type_diagnostic"));
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in SetDetails - ClinicHistoryDetailsCard : " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

}
