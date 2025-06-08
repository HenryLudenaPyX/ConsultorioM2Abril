package com.prototype.cm2a.components;

import com.prototype.cm2a.database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmissionDetailsCard {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAddressPeople;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtArrivalMethod;

    @FXML
    private TextField txtBirthDate;

    @FXML
    private TextField txtCanton;

    @FXML
    private TextField txtCivilStatus;

    @FXML
    private TextField txtDateAttention;

    @FXML
    private TextField txtFirstSurname;

    @FXML
    private TextField txtFontInformation;

    @FXML
    private TextField txtHealthInsurance;

    @FXML
    private TextField txtHourAttention;

    @FXML
    private TextField txtIdentityCard;

    @FXML
    private TextField txtInstitutionOrPeople;

    @FXML
    private TextField txtInstitutionOrPeoplePhone;

    @FXML
    private TextField txtInstruction;

    @FXML
    private TextField txtLastSurname;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtMailPeople;

    @FXML
    private TextField txtNamePeople;

    @FXML
    private TextField txtNames;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtOcupation;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPhonePeople;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtRelationship;

    @FXML
    private TextField txtSex;

    public void setDetails(String identityNumber) {
        String query = "SELECT * FROM pacient WHERE idNumber = ?";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            if (con != null) {
                pstmt.setString(1, identityNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        txtFirstSurname.setText(rs.getString("paternalLastname"));
                        txtLastSurname.setText(rs.getString("maternalLastname"));
                        txtNames.setText(rs.getString("names"));
                        txtNationality.setText(rs.getString("nationality"));
                        txtIdentityCard.setText(rs.getString("idNumber"));
                        txtBirthDate.setText(rs.getString("birthDate"));
                        txtAddress.setText(rs.getString("address"));
                        txtCanton.setText(rs.getString("canton"));
                        txtProvince.setText(rs.getString("province"));
                        txtPhone.setText(rs.getString("phoneNumber"));
                        txtMail.setText(rs.getString("email"));
                        txtDateAttention.setText(rs.getString("attentionDate"));
                        txtHourAttention.setText(rs.getString("attentionTime"));
                        txtAge.setText(rs.getString("age"));
                        txtSex.setText(rs.getString("gender"));
                        txtCivilStatus.setText(rs.getString("maritalStatus"));
                        txtInstruction.setText(rs.getString("education"));
                        txtOcupation.setText(rs.getString("occupation"));
                        txtHealthInsurance.setText(rs.getString("healthInsurance"));
                        txtNamePeople.setText(rs.getString("notificationPersonName"));
                        txtRelationship.setText(rs.getString("relationshipPerson"));
                        txtAddressPeople.setText(rs.getString("notificationPersonAddress"));
                        txtPhonePeople.setText(rs.getString("notificationPersonPhoneNumber"));
                        txtMailPeople.setText(rs.getString("notificationPersonEmail"));
                        txtArrivalMethod.setText(rs.getString("arrivalMethod"));
                        txtFontInformation.setText(rs.getString("informationSource"));
                        txtInstitutionOrPeople.setText(rs.getString("institutionOrPersonDeliveringPatient"));
                        txtInstitutionOrPeoplePhone.setText(rs.getString("deliveringPersonPhoneNumber"));
                    }
                }
                pstmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in setDetailsUpdate: " + e.getMessage());
        }
    }

}
