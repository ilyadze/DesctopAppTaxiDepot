package com.example.laba_5_sem_2_javafx;

import com.example.laba_5_sem_2_javafx.entity.engine.Engine;
import com.example.laba_5_sem_2_javafx.entity.taxiFleet.TaxiFleet;
import com.example.laba_5_sem_2_javafx.enums.BodyType;
import com.example.laba_5_sem_2_javafx.enums.Colors;
import com.example.laba_5_sem_2_javafx.enums.Manufacture;
import com.example.laba_5_sem_2_javafx.logic.Manager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class UsersAdminView {

    private ObservableList<User> usersList = FXCollections.observableArrayList();
    @FXML
    private Button AdminStatementButton;

    @FXML
    private Button BlockStatementButton;

    @FXML
    private TableColumn<User, String> BlockStatementColumn;

    @FXML
    private Button DeleteAccountButton;

    @FXML
    private TableColumn<User, String> LoginColumn;

    @FXML
    private TableColumn<User, String> RoleStatementColumn;

    @FXML
    private Button buttonBack;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Label lbl;

    @FXML
    void checkButtonBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-admin-view.fxml"));
//        loader.setLocation();
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (buttonBack.getScene().getWindow());
            stage.setTitle("Menu!");

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void initialize () throws ClassNotFoundException {
//        TaxiFleet taxiFleet = Manager.readTaxiFleet();
        List<User> users = Manager.readUserList();
        LoginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        BlockStatementColumn.setCellValueFactory(new PropertyValueFactory<User, String>("blockStatement"));
        RoleStatementColumn.setCellValueFactory(new PropertyValueFactory<User, String>( "roleStatement"));

        initCarsList(users);
        usersTable.setItems(usersList);
    }

    private void initCarsList(List<User> users) {
        for (User user:users) {
            usersList.add(user);
        }
    }



    @FXML
    void checkAdminStatementButton(ActionEvent event) {
        TableView.TableViewSelectionModel<User> selectionModel = usersTable.getSelectionModel();
        User user = selectionModel.getSelectedItem();

        if (user.getLogin().equals("admin")) {
            return;
        }
        List<User> users = Manager.readUserList();

        if (user.getRoleStatement().equals("admin")) {
            user.setRoleStatement("not admin");
        }
        else {
            user.setRoleStatement("admin");
        }

        for(User el : users) {
            if(user.getLogin().equals(el.getLogin())) {
                String blockStatement = el.getRoleStatement().toLowerCase().equals("admin") ? "Not admin" : "admin";
                el.setRoleStatement(blockStatement);
                break;
            }
        }
        Manager.saveUserList(users);
        usersTable.refresh();
    }

    @FXML
    void checkBlockStatementButton(ActionEvent event) {
        TableView.TableViewSelectionModel<User> selectionModel = usersTable.getSelectionModel();
        User user = selectionModel.getSelectedItem();
        if (user.getLogin().equals("admin")) {
            return;
        }

        List<User> users = Manager.readUserList();

        if (user.getBlockStatement().equals("Not blocked")) {
            user.setBlockStatement("Blocked");
        }
        else {
            user.setBlockStatement("Not blocked");
        }
        for(User el : users) {
            if(user.getLogin().equals(el.getLogin())) {
                String blockStatement = el.getBlockStatement().toLowerCase().equals("blocked") ? "Not blocked" : "blocked";
                el.setBlockStatement(blockStatement);
                break;
            }
        }
        Manager.saveUserList(users);
        usersTable.refresh();
    }

    @FXML
    void checkDeleteAccountButton(ActionEvent event) {
        TableView.TableViewSelectionModel<User> selectionModel = usersTable.getSelectionModel();
        User user = selectionModel.getSelectedItem();
        List<User> users = Manager.readUserList();
        if (user.getLogin().equals("admin")) {
            return;
        }
        int index = 0;
        for(User el : users) {
            if(user.getLogin().equals(el.getLogin())) {
                break;
            }
            index++;
        }
        users.remove(index);
        usersList.remove(user);

//        for(User el : users) {
//            if(user.getLogin().equals(el.getLogin())) {
//                user
//                break;
//            }
//        }
        Manager.saveUserList(users);
        usersTable.refresh();
    }

}
