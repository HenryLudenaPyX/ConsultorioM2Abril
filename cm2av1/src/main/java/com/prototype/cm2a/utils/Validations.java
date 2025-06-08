package com.prototype.cm2a.utils;

import com.prototype.cm2a.database.DBConnection;
import javafx.scene.control.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Validations {

    public static boolean checkPressure (TextField txt, Label lbl){
        if (!txt.getText().matches("^\\d{2,3}/\\d{2,3}\\smmHg$")) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!txt.getText().matches("^\\d{2,3}/\\d{2,3}\\smmHg$")) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkPulse (TextField txt, Label lbl){
        if (!txt.getText().matches("^\\d{2,3}$")) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!txt.getText().matches("^\\d{2,3}$")) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkTemperature (TextField txt, Label lbl){
        if (!txt.getText().matches("^\\d{1,2}\\sC$")) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!txt.getText().matches("^\\d{1,2}\\sC$")) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkCIE (TextField txt, Label lbl){
        if (!txt.getText().matches("^[A-Z]\\d{2}$")) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!txt.getText().matches("^[A-Z]\\d{2}$")) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkDescription (TextField txt, Label lbl){
        if (txt.getText().isEmpty() && txt.getText().isBlank()) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (txt.getText().isEmpty() && txt.getText().isBlank()) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkPreDef (ComboBox<String> txt, Label lbl){
        if (txt.getValue().isEmpty() && txt.getValue().isBlank()) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (txt.getValue().isEmpty() && txt.getValue().isBlank()) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                    //return false;
                }
            }
        });
        return true;
    }
    public static boolean checkDateVS (DatePicker txt, Label lbl){
        LocalDate date = txt.getValue();
        if (!(date != null && !date.isAfter(LocalDate.now()))) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            return false;
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!(date != null && !date.isAfter(LocalDate.now()))) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return true;
    }

    public static boolean checkTwoWords (String names){
        if(names.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkIva (String iva){
        if (iva.matches("^\\d{1}\\.\\d{2}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIce (String ice){
        if (ice.matches("^\\d{1,4}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkCodeMedicament(String code){
        if (code.matches("^2[0-9]{2}$")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkCodeTypeAttention(String code){
        if (code.matches("^1[0-9]{2}$")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkCompleteName (String names){
        if(names.matches("^([a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)|([a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)|([a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+\\s[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+)$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkEmail (String email){
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}(\\.[a-zA-Z]{2,4}){0,2}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkOneWord (String lastName){
        if (lastName.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkCodeClinic (String code){
        if (code.matches("^HC\\d{4}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkDate (String date){
        if (date.matches("^(0[1-9]{1,2}|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkPrice (String price){
        if (price.matches("^\\d{1,4}\\.\\d{2}$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkHour (String hour){
        if (hour.matches("^([0-1]?\\d|2[0-3]):([0-5]\\d)$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkAge (String age){
        if (age.matches("^[0-9]{1,3}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkPhone (String phone){
        if (phone.matches("09[0-9]{8}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkPhoneV2(String phone) {
        if (phone.matches("^09[0-9]{8}$|^NA$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIdNumber (String identityCard){
        int digitos;
        int digitoValidador;
        int sumaDigitos = 0;
        int resultadoDigitos;
        if (identityCard.length() == 10) {
            int provincia = Integer.parseInt(identityCard.substring(0, 2));
            if (provincia < 1 || (provincia > 24 && provincia != 30)) {
                return false;
            }
        }
        if (identityCard.length() == 10){
            for (int i = 0; i < identityCard.length() - 1; i++){
                digitos = Integer.parseInt(String.valueOf(identityCard.charAt(i)));
                if (i % 2 == 0) {
                    digitos = digitos * 2;
                    if(digitos > 9){
                        digitos = digitos - 9;
                        sumaDigitos = sumaDigitos + digitos;
                    } else {
                        sumaDigitos = sumaDigitos + digitos;
                    }
                } else {
                    sumaDigitos = sumaDigitos + digitos;
                }
            }
            if(sumaDigitos % 10 == 0){
                resultadoDigitos = 0;
            }else{
                resultadoDigitos = 10 - (sumaDigitos % 10);
            }
            digitoValidador = Integer.parseInt(String.valueOf(identityCard.charAt(9)));
            if (resultadoDigitos == digitoValidador) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkWords (String hour){
        if (hour.matches("^[a-zA-ZÑñáéíóúÁÉÍÓÚüÜ\\s]+$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkPassword (String password){
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#\\$!\\-])[a-zA-Z\\d#!$-]{10,20}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkNumber(String number){
        if (number.matches("^\\d{1,2}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkNumberClinic(String number){
        if (number.matches("^\\d{1}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkStock(String stock){
        if (stock.matches("^\\d{1,4}$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkDateV2(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña", e);
        }
    }

    public static boolean checkExistenceIdentityCard(String identityCard, String module){
        String query = "SELECT EXISTS (SELECT 1 FROM " + module + " WHERE idNumber = ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, identityCard);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkExistenceIdentityCard: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkExistence(String identityCard, String code , String module){
        String query = "SELECT EXISTS (SELECT 1 FROM " + module + " WHERE idNumber = ? AND code = ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, identityCard);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1) && rs.getBoolean(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkExistenceIdentityCard: " + e.getMessage());
        }
        return false;
    }

    public static boolean checkExistenceCodeClinic(String identityCard, String module){
        String query = "SELECT EXISTS (SELECT 1 FROM " + module + " WHERE id = ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, identityCard);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkExistenceIdentityCard: " + e.getMessage());
        }
        return false;
    }

    public static boolean checkExistenceCode(String identityCard, String module){
        String query = "SELECT EXISTS (SELECT 1 FROM " + module + " WHERE id = ?)";

        try (Connection con = DBConnection.connection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(identityCard));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing query in checkExistenceIdentityCard: " + e.getMessage());
        }
        return false;
    }

    public static List<String> validateSimilarPasswords (Label lblPass, TextField txtPass, Label lblPassC,
                                                         TextField txtPassC, List<String> errors){
        if(!txtPass.getText().equalsIgnoreCase(txtPassC.getText())) {
            lblPassC.setText("Las contraseñas no coinciden");
            lblPass.setText("Las contraseñas no coinciden");
            txtPass.setStyle("-fx-border-color: red red red red;");
            txtPassC.setStyle("-fx-border-color: red red red red;");
            errors.add("Passwords do not match");
        }
        txtPass.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtPass.setStyle("-fx-border-color: #3F84B7;");
                lblPass.setVisible(false);
            } else {
                if (!txtPass.getText().equalsIgnoreCase(txtPassC.getText())) {
                    lblPass.setVisible(true);
                    txtPass.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        txtPassC.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtPassC.setStyle("-fx-border-color: #3F84B7;");
                lblPassC.setVisible(false);
            } else {
                if (!txtPass.getText().equalsIgnoreCase(txtPassC.getText())) {
                    lblPassC.setVisible(true);
                    txtPassC.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateExistenceIdentityCard (Label lblIdentityCard, TextField txtIdentityCard, List<String> errors, String module){
        boolean isValid = Validations.checkIdNumber(txtIdentityCard.getText());

        if (isValid) {
            if (Validations.checkExistenceIdentityCard(txtIdentityCard.getText(), module)) {
                lblIdentityCard.setVisible(true);
                lblIdentityCard.setText("El número de cédula ya existe");
                txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                errors.add("Error: El número de cédula ya existe");
            }
        } else {
            Validations.validateIdentityCard(txtIdentityCard, lblIdentityCard, errors);
        }
        txtIdentityCard.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                lblIdentityCard.setVisible(false);
            } else {
                if (!Validations.checkIdNumber(txtIdentityCard.getText())) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Número de cédula no válido");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else if (Validations.checkExistenceIdentityCard(txtIdentityCard.getText(), module)) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("El número de cédula ya existe");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else {
                    txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                    lblIdentityCard.setVisible(false);
                }
            }
        });
        return errors;
    }

    public static List<String> validateExistenceCode (TextField txtIdentityCard, Label lblIdentityCard, List<String> errors, String module){
        boolean isValid = Validations.checkCodeMedicament(txtIdentityCard.getText());

        if (isValid) {
            if (Validations.checkExistenceCode(txtIdentityCard.getText(), module)) {
                lblIdentityCard.setVisible(true);
                lblIdentityCard.setText("El código ya existe");
                txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                errors.add("Error: El código ya existe");
            }
        } else {
            Validations.validateCodeMedicament(txtIdentityCard, lblIdentityCard, errors);
        }
        txtIdentityCard.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                lblIdentityCard.setVisible(false);
            } else {
                if (!Validations.checkCodeMedicament(txtIdentityCard.getText())) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Código no válido");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else if (Validations.checkExistenceCode(txtIdentityCard.getText(), module)) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("código ya existe");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else {
                    txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                    lblIdentityCard.setVisible(false);
                }
            }
        });
        return errors;
    }

    public static List<String> validateExistenceTypeAttention (TextField txtIdentityCard, Label lblIdentityCard, List<String> errors, String module){
        boolean isValid = Validations.checkCodeTypeAttention(txtIdentityCard.getText());

        if (isValid) {
            if (Validations.checkExistenceCode(txtIdentityCard.getText(), module)) {
                lblIdentityCard.setVisible(true);
                lblIdentityCard.setText("El código ya existe");
                txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                errors.add("Error: El código ya existe");
            }
        } else {
            Validations.validateCodeTypeAttention(txtIdentityCard, lblIdentityCard, errors);
        }
        txtIdentityCard.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                lblIdentityCard.setVisible(false);
            } else {
                if (!Validations.checkCodeTypeAttention(txtIdentityCard.getText())) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Código no válido");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else if (Validations.checkExistenceCode(txtIdentityCard.getText(), module)) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("código ya existe");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else {
                    txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                    lblIdentityCard.setVisible(false);
                }
            }
        });
        return errors;
    }

    public static List<String> validateExistenceCodeClinic (TextField txtIdentityCard, Label lblIdentityCard, List<String> errors, String module){
        boolean isValid = Validations.checkCodeClinic(txtIdentityCard.getText());

        if (isValid) {
            if (Validations.checkExistenceCodeClinic(txtIdentityCard.getText(), module)) {
                lblIdentityCard.setVisible(true);
                lblIdentityCard.setText("El código ya existe");
                txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                errors.add("Error: El código ya existe");
            }
        } else {
            Validations.validateCodeClinic(txtIdentityCard, lblIdentityCard, errors);
        }
        txtIdentityCard.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                lblIdentityCard.setVisible(false);
            } else {
                if (!Validations.checkCodeClinic(txtIdentityCard.getText())) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Código no válido");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else if (Validations.checkExistenceCodeClinic(txtIdentityCard.getText(), module)) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Código ya existe");
                    txtIdentityCard.setStyle("-fx-border-color: transparent red red red;");
                } else {
                    txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                    lblIdentityCard.setVisible(false);
                }
            }
        });
        return errors;
    }

    public static List<String> validateNoExistenceIdentityCard (Label lblIdentityCard, TextField txtIdentityCard, List<String> errors, String module){
        boolean isValid = Validations.checkIdNumber(txtIdentityCard.getText());

        if (isValid) {
            if (!Validations.checkExistenceIdentityCard(txtIdentityCard.getText(), module)) {
                lblIdentityCard.setVisible(true);
                lblIdentityCard.setText("El número de cédula no existe");
                txtIdentityCard.setStyle("-fx-border-color: red red red red;");
                errors.add("Error: El número de cédula ya existe");
            }
        } else {
            Validations.validateIdentityCard(txtIdentityCard, lblIdentityCard, errors);
        }
        txtIdentityCard.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                lblIdentityCard.setVisible(false);
            } else {
                if (!Validations.checkIdNumber(txtIdentityCard.getText())) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("Número de cédula no válido");
                    txtIdentityCard.setStyle("-fx-border-color: red red red red;");
                } else if (!Validations.checkExistenceIdentityCard(txtIdentityCard.getText(), module)) {
                    lblIdentityCard.setVisible(true);
                    lblIdentityCard.setText("El número de cédula no existe");
                    txtIdentityCard.setStyle("-fx-border-color: red red red red;");
                } else {
                    txtIdentityCard.setStyle("-fx-border-color: #3F84B7;");
                    lblIdentityCard.setVisible(false);
                }
            }
        });
        return errors;
    }

    public static List<String> validateNumber (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkNumber(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkNumber(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateIva (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkIva(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkIva(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateIce (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkIce(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkIce(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateCodeClinic (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkCodeClinic(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkCodeClinic(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateNumberClinic (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkNumberClinic(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkNumberClinic(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateCodeMedicament (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkCodeMedicament(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkCodeMedicament(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateCodeTypeAttention (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkCodeTypeAttention(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkCodeTypeAttention(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validatePrice (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkPrice(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkPrice(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateStock (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkStock(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkStock(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validatePassword (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkPassword(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: red red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkPassword(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: red red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateOneWord (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkOneWord(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid One Word");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkOneWord(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateTwoWords (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkTwoWords(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkTwoWords(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateEmail (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkEmail(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkEmail(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateCompleteName (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkCompleteName(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkCompleteName(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateIdentityCard (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkIdNumber(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkIdNumber(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateDateV2(DatePicker datePicker, Label lbl, List<String> errors) {
        if (!Validations.checkDateV2(datePicker.getValue())) {
            lbl.setVisible(true);
            datePicker.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Fecha inválida");
        }
        datePicker.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                datePicker.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkDateV2(datePicker.getValue())) {
                    lbl.setVisible(true);
                    datePicker.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateDateV1(DatePicker datePicker, Label lbl, List<String> errors) {
        LocalDate date = datePicker.getValue();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (date == null || !Validations.checkDate(date.format(dateFormatter))) {
            lbl.setVisible(true);
            datePicker.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Fecha inválida");
        }

        datePicker.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                datePicker.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                LocalDate newDate = datePicker.getValue();
                if (newDate == null || !Validations.checkDate(newDate.format(dateFormatter))) {
                    lbl.setVisible(true);
                    datePicker.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateAge (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkAge(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkAge(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateAgeV2(DatePicker birth, TextField txt, Label lbl, List<String> errors) {
        LocalDate birthDate = birth.getValue();
        int calculatedAge = calculateAge(birthDate, LocalDate.now());
        int enteredAge = Integer.parseInt(txt.getText());
        if (!Validations.checkAge(txt.getText()) || enteredAge > calculatedAge) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkAge(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        }
        return 0;
    }

    public static List<String> validatePhone (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkPhone(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkPhone(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validatePhoneV2 (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkPhoneV2(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkPhoneV2(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateHour (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkHour(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkHour(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateNulls (TextField txt, Label lbl, List<String> errors){
        if (txt.getText().isEmpty() && txt.getText().isBlank()) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (txt.getText().isEmpty() && txt.getText().isBlank()) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateNullsV2 (TextArea txt, Label lbl, List<String> errors){
        if (txt.getText().isEmpty()) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (txt.getText().isEmpty()) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateComboBox (ComboBox<String> txt, Label lbl, List<String> errors){
        if (txt.getValue() == null) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Combo Box");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (txt.getValue() == null) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static List<String> validateWords (TextField txt, Label lbl, List<String> errors){
        if (!Validations.checkWords(txt.getText())) {
            lbl.setVisible(true);
            txt.setStyle("-fx-border-color: transparent red red red;");
            errors.add("Error: Invalid Two Words");
        }
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txt.setStyle("-fx-border-color: #3F84B7;");
                lbl.setVisible(false);
            } else {
                if (!Validations.checkWords(txt.getText())) {
                    lbl.setVisible(true);
                    txt.setStyle("-fx-border-color: transparent red red red;");
                }
            }
        });
        return errors;
    }

    public static void restartStyles (TextField txt, Label lbl){
        txt.setStyle("-fx-border-color: #3F84B7;");
        lbl.setVisible(false);
    }

    public static void restartStylesV2 (ComboBox txt, Label lbl){
        txt.setValue(null);
        txt.setStyle("-fx-border-color: #3F84B7;");
        lbl.setVisible(false);
    }

    public static void restartStylesV3 (TextArea txt, Label lbl){
        txt.setStyle(null);
        txt.setStyle("-fx-border-color: #3F84B7;");
        lbl.setVisible(false);
    }

    public static void restartStylesV4 (DatePicker txt, Label lbl){
        txt.setStyle(null);
        txt.setStyle("-fx-border-color: #3F84B7;");
        lbl.setVisible(false);
    }
}