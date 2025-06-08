package com.prototype.cm2a.utils;

import com.prototype.cm2a.database.DBConnection;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Query {

    public static void queryRegisterPacient(TextField txtFirstSurname, TextField txtLastSurname, TextField txtNames, TextField txtNationality, TextField txtIdentityCard, DatePicker txtBirthDate,
                                            TextField txtAddress, TextField txtCanton, TextField txtProvince, TextField txtPhone, TextField txtMail, DatePicker txtDateAttention, TextField txtHourAttention,
                                            TextField txtAge, ComboBox<String> txtSex, ComboBox<String> txtCivilStatus, ComboBox<String> txtInstruction, TextField txtOcupation, ComboBox<String> txtHealthInsurance,
                                            TextField txtNamePeople, TextField txtRelationship, TextField txtAddressPeople, TextField txtPhonePeople, TextField txtMailPeople, ComboBox<String> txtArrivalMethod,
                                            ComboBox<String> txtFontInformation, TextField txtInstitutionOrPeople, TextField txtInstitutionOrPeoplePhone){
        String transaction = "INSERT INTO pacient (" +
                "paternalLastName, maternalLastName, names, nationality, idNumber, birthDate, address, canton, province, " +
                "phoneNumber, email, attentionDate, attentionTime, age, gender, maritalStatus, education, occupation, " +
                "healthInsurance, notificationPersonName, relationshipPerson, notificationPersonAddress, " +
                "notificationPersonPhoneNumber, notificationPersonEmail, arrivalMethod, informationSource, " +
                "institutionOrPersonDeliveringPatient, deliveringPersonPhoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(transaction)) {
            if (con != null) {
                pstmt.setString(1, txtFirstSurname.getText());
                pstmt.setString(2, txtLastSurname.getText());
                pstmt.setString(3, txtNames.getText());
                pstmt.setString(4, txtNationality.getText());
                pstmt.setString(5, txtIdentityCard.getText());

                LocalDate birthDate = txtBirthDate.getValue();
                if (birthDate != null) {
                    pstmt.setDate(6, Date.valueOf(birthDate));
                } else {
                    pstmt.setNull(6, java.sql.Types.DATE);
                }

                pstmt.setString(7, txtAddress.getText());
                pstmt.setString(8, txtCanton.getText());
                pstmt.setString(9, txtProvince.getText());
                pstmt.setString(10, txtPhone.getText());
                pstmt.setString(11, txtMail.getText());

                LocalDate attentionDate = txtDateAttention.getValue();
                if (attentionDate != null) {
                    pstmt.setDate(12, Date.valueOf(attentionDate));
                } else {
                    pstmt.setNull(12, java.sql.Types.DATE);
                }

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime localAttentionTime = LocalTime.parse(txtHourAttention.getText(), timeFormatter);
                pstmt.setTime(13, Time.valueOf(localAttentionTime));

                pstmt.setInt(14, Integer.parseInt(txtAge.getText()));
                pstmt.setString(15, txtSex.getValue());
                pstmt.setString(16, txtCivilStatus.getValue());
                pstmt.setString(17, txtInstruction.getValue());
                pstmt.setString(18, txtOcupation.getText());
                pstmt.setString(19, txtHealthInsurance.getValue());
                pstmt.setString(20, txtNamePeople.getText());
                pstmt.setString(21, txtRelationship.getText());
                pstmt.setString(22, txtAddressPeople.getText());
                pstmt.setString(23, txtPhonePeople.getText());
                pstmt.setString(24, txtMailPeople.getText());
                pstmt.setString(25, txtArrivalMethod.getValue());
                pstmt.setString(26, txtFontInformation.getValue());
                pstmt.setString(27, txtInstitutionOrPeople.getText());
                pstmt.setString(28, txtInstitutionOrPeoplePhone.getText());
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void queryUpdatePacient(String txtIdentityCard, TextField txtAddress, TextField txtPhone, TextField txtMail,
                                          TextField txtNamePeople, TextField txtRelationship, TextField txtAddressPeople, TextField txtPhonePeople, TextField txtMailPeople){
        String transaction = "UPDATE pacient SET address = ?, phoneNumber = ?, email = ?, " +
                            "notificationPersonName = ?, relationshipPerson = ?, notificationPersonAddress = ?, " +
                            "notificationPersonPhoneNumber = ?, notificationPersonEmail = ? WHERE idNumber = ?";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(transaction)) {
            if (con != null) {
                pstmt.setString(1, txtAddress.getText());
                pstmt.setString(2, txtPhone.getText());
                pstmt.setString(3, txtMail.getText());
                pstmt.setString(4, txtNamePeople.getText());
                pstmt.setString(5, txtRelationship.getText());
                pstmt.setString(6, txtAddressPeople.getText());
                pstmt.setString(7, txtPhonePeople.getText());
                pstmt.setString(8, txtMailPeople.getText());
                pstmt.setString(9, txtIdentityCard);
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean compareChanges(String txtIdentityCard, TextField txtAddress, TextField txtPhone, TextField txtMail,
                                         TextField txtNamePeople, TextField txtRelationship, TextField txtAddressPeople, TextField txtPhonePeople, TextField txtMailPeople){
        String query = "SELECT address, phoneNumber, email, notificationPersonName, relationshipPerson, notificationPersonAddress, " +
                "notificationPersonPhoneNumber, notificationPersonEmail FROM pacient WHERE idNumber = ?";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, txtIdentityCard);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String currentAddress = rs.getString("address");
                String currentPhone = rs.getString("phoneNumber");
                String currentMail = rs.getString("email");
                String currentNamePeople = rs.getString("notificationPersonName");
                String currentRelationship = rs.getString("relationshipPerson");
                String currentAddressPeople = rs.getString("notificationPersonAddress");
                String currentPhonePeople = rs.getString("notificationPersonPhoneNumber");
                String currentMailPeople = rs.getString("notificationPersonEmail");

                return !currentAddress.equalsIgnoreCase(txtAddress.getText()) ||
                        !currentPhone.equalsIgnoreCase(txtPhone.getText()) ||
                        !currentMail.equalsIgnoreCase(txtMail.getText()) ||
                        !currentNamePeople.equalsIgnoreCase(txtNamePeople.getText()) ||
                        !currentRelationship.equalsIgnoreCase(txtRelationship.getText()) ||
                        !currentAddressPeople.equalsIgnoreCase(txtAddressPeople.getText()) ||
                        !currentPhonePeople.equalsIgnoreCase(txtPhonePeople.getText()) ||
                        !currentMailPeople.equalsIgnoreCase(txtMailPeople.getText());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Set<String> suggestionsIdentityCard(){
        Set<String> suggestion = new HashSet<>();
        String query = "SELECT idNumber FROM pacient";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                suggestion.add(rs.getString("idNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suggestion;
    }

    public static void queryRegisterUser(TextField txtIdentityCardUser, TextField txtNamesUser, TextField txtLastnamesUser,
                                         ComboBox<String>  txtGender, TextField txtPhone, TextField txtMail, TextField txtAdrress,
                                         TextField txtProfession, TextField txtExperience,ComboBox<String>  txtPermissionRol){
        String transaction = "INSERT INTO Users (" +
                "idNumber, names, surnames, gender, phone, email, address, profession, experienceYear, " +
                "username, rol ,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(transaction)) {
            if (con != null) {
                pstmt.setString(1, txtIdentityCardUser.getText());
                pstmt.setString(2, txtNamesUser.getText());
                pstmt.setString(3, txtLastnamesUser.getText());
                pstmt.setString(4, txtGender.getValue());
                pstmt.setString(5, txtPhone.getText());
                pstmt.setString(6, txtMail.getText());
                pstmt.setString(7, txtAdrress.getText());
                pstmt.setString(8, txtProfession.getText());
                pstmt.setInt(9, Integer.parseInt(txtExperience.getText()));
                pstmt.setString(10, txtIdentityCardUser.getText());
                pstmt.setString(11, txtPermissionRol.getValue());
                pstmt.setString(12, "Activo");
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public static void createUser(String userName, String password){
//        String transaction = "INSERT INTO users VALUES (?, ?)";
//        try (Connection con = DBConnection.connection();
//             PreparedStatement pstmt = con.prepareStatement(transaction)){
//            if (con != null) {
//                pstmt.setString(1, password);
//                pstmt.setString(2, identityCard);
//                int affectedRows = pstmt.executeUpdate();
//                System.out.println("Rows affected: " + affectedRows);
//            }
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    public static void updatePassword(String identityCard, String newPassword){
        String transaction = "UPDATE users SET password = ? WHERE idNumber = ?";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(transaction)) {
            if (con != null) {
                pstmt.setString(1, newPassword);
                pstmt.setString(2, identityCard);
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean compareChangesUser(String txtIdentityCard, TextField txtPhone, TextField txtEmail, TextField txtAddress,
                                         TextField txtExperience, ComboBox<String> txtRole){
        String query = "SELECT phone, email, address, experienceYear, rol FROM users WHERE idNumber = ?";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, txtIdentityCard);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String currentPhone = rs.getString("phone");
                String currentMail = rs.getString("email");
                String currentAddress = rs.getString("address");
                String currentExperienceYear = rs.getString("experienceYear");
                String currentRol = rs.getString("rol");

                return !currentAddress.equalsIgnoreCase(txtAddress.getText()) ||
                        !currentPhone.equalsIgnoreCase(txtPhone.getText()) ||
                        !currentMail.equalsIgnoreCase(txtEmail.getText()) ||
                        !currentExperienceYear.equalsIgnoreCase(txtExperience.getText()) ||
                        !currentRol.equalsIgnoreCase(txtRole.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void queryUpdateUser(String txtIdentityCard, TextField txtPhone, TextField txtEmail, TextField txtAddress,
                                          TextField txtExperience, ComboBox<String> txtRole){
        String transaction = "UPDATE users SET phone = ?, email = ?, address = ?, " +
                "experienceYear = ?, rol = ? WHERE idNumber = ?";
        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(transaction)) {
            if (con != null) {
                pstmt.setString(1, txtPhone.getText());
                pstmt.setString(2, txtEmail.getText());
                pstmt.setString(3, txtAddress.getText());
                pstmt.setInt(4, Integer.parseInt(txtExperience.getText()));
                pstmt.setString(5, txtRole.getValue());
                pstmt.setString(6, txtIdentityCard);
                int affectedRows = pstmt.executeUpdate();
                System.out.println("Rows affected: " + affectedRows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
