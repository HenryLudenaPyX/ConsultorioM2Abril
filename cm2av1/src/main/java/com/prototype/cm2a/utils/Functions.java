package com.prototype.cm2a.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Functions {

    public static void updateDateTime(Label lblHour){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.001), e -> {
            String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Platform.runLater(() -> lblHour.setText(fechaActual));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        lblHour.setVisible(true);
    }

}
