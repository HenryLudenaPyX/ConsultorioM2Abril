package com.prototype.cm2a.components;

import com.prototype.cm2a.database.DBConnection;
import com.prototype.cm2a.models.MedicalAttention;
import com.prototype.cm2a.utils.Validations;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MedicalAttentionCard {

    @FXML
    private TextField txtCradNumberAtte;

    @FXML
    private DatePicker txtDateAttention;

    @FXML
    private TextArea txtEvolution;

    @FXML
    private TextField txtHourAttention;

    @FXML
    private TextArea txtMedicaments;

    @FXML
    private TextField txtNameMedic;

    @FXML
    private TextField txtNameMedic1;

    @FXML
    private TextArea txtPreinscription;

    private MedicalAttention medicalAttention;

    public void setDetails(MedicalAttention medicalAttention){
        this.medicalAttention = medicalAttention;
        txtCradNumberAtte.setText(medicalAttention.getId());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(medicalAttention.getDate(), inputFormatter);
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txtDateAttention.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? displayFormatter.format(date) : "";
            }
            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, displayFormatter) : null;
            }
        });
        txtDateAttention.setValue(date);
        txtHourAttention.setText(medicalAttention.getTime());
        txtEvolution.setText(medicalAttention.getEvolution());
        txtPreinscription.setText(medicalAttention.getPreinscription());
        txtMedicaments.setText(medicalAttention.getMedication());
        txtNameMedic.setText(medicalAttention.getMedic());
        txtNameMedic1.setText(medicalAttention.getMedic());
    }
}
