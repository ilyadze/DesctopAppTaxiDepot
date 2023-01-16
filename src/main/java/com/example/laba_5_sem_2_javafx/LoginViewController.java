package com.example.laba_5_sem_2_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRegistration;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    void checkLogIn(ActionEvent event) {
//        List<User> userList = new ArrayList<>();
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream("UsersData.txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            List<User> users = (List<User>) (objectInputStream.readObject());
//            for(User user : users) {
//                userList.add(user);
//            }
//            objectInputStream.close();
//            fileInputStream.close();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        String login = loginField.getText();
        String password = passwordField.getText();
        if(login.equals("") || password.equals("")) {
            errorLabel.setText("Enter login and password");
            return;
        }
        User user = User.existenceCheck(login, password);
        if (user == null){
            errorLabel.setText("Login or password entered incorrectly");
            return;
        }

        if (user.getBlockStatement().toLowerCase().equals("blocked")) {
            errorLabel.setText("Account is blocked");
            return;
        }
        FXMLLoader loader;
        if (user.getRoleStatement().equals("admin")) {
            loader = new FXMLLoader(getClass().getResource("menu-admin-view.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("menu-user-view.fxml"));
//        loader.setLocation();

        }
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (buttonLogin.getScene().getWindow());
            stage.setTitle("Menu!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void checkRegistration(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (buttonRegistration.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setTitle("Registration!");

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}