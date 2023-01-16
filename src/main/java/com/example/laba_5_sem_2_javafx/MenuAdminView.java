package com.example.laba_5_sem_2_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuAdminView {

    @FXML
    private Button usersButton;

    @FXML
    private Button carsButton;

    @FXML
    private Button exitButton;

    @FXML
    void checkButtonUsers(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("users-admin-view.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (usersButton.getScene().getWindow());
            stage.setTitle("Users!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void checkButtonCars(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("taxis-admin-view.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (carsButton.getScene().getWindow());
            stage.setTitle("Taxis!");

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void checkButtonExit(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (exitButton.getScene().getWindow());
            stage.setTitle("Autorization!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
