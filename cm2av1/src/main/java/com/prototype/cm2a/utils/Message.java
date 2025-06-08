package com.prototype.cm2a.utils;

import javafx.scene.control.Alert;

public class Message {

    public void setWarning(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText("Importante");
        alerta.setContentText(mensaje);
        alerta.show();
    }

    public void setError(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText("Importante");
        alerta.setContentText(mensaje);
        alerta.show();
    }

    public void setInformation(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Importante");
        alerta.setContentText(mensaje);
        alerta.show();
    }
}