package com.example.laba_5_sem_2_javafx;

import com.example.laba_5_sem_2_javafx.entity.facade.AuthenticationFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationViewController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button buttonBack;


    @FXML
    void checkButtonBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
//        loader.setLocation();
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (buttonBack.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void checkSignUp(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if(login.equals("") || password.equals("")) {
            return;
        }
        User user = User.existenceCheck(login, password);
        if (AuthenticationFacade.Registration(login, password)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
//        loader.setLocation();
            try {
                Parent root = loader.load();
                Stage stage = (Stage) (buttonBack.getScene().getWindow());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {

        }
    }

}
