package com.example.laba_5_sem_2_javafx;

import com.example.laba_5_sem_2_javafx.entity.engine.Engine;
import com.example.laba_5_sem_2_javafx.entity.taxiFleet.TaxiFleet;
import com.example.laba_5_sem_2_javafx.enums.BodyType;
import com.example.laba_5_sem_2_javafx.enums.Colors;
import com.example.laba_5_sem_2_javafx.enums.Manufacture;
import com.example.laba_5_sem_2_javafx.logic.Actions;
import com.example.laba_5_sem_2_javafx.logic.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxisAdminView {

    private ObservableList<Car> carsList = FXCollections.observableArrayList();

    private boolean isHaveFilterByColor = false;

    private boolean isHaveFilterByManufacture = false;

    @FXML
    private Button AddCarButton;

    @FXML
    private TableColumn<Car, Colors> ColorColumn;

    @FXML
    private Button DeleteCatButton;

    @FXML
    private Button EditCarButton;

    @FXML
    private TableColumn<Car, BodyType> bodyTypeColumn;

    @FXML
    private Button buttonBack;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, Engine> engineColumn;

    @FXML
    private Button deleteFiltrationButton;

    @FXML
    private TableColumn<Car, Manufacture> manufactureColumn;

    @FXML
    private TableColumn<Car, BigDecimal> priceColumn;

    @FXML
    private Label totalCostLabel;

    @FXML
    private TableColumn<Car, Integer> yearOfIssueColumn;

    @FXML
    private ChoiceBox<Colors> colors;

    @FXML
    private ChoiceBox<Manufacture> manufacture;


    @FXML
    private ChoiceBox<String> OrderBox;


    @FXML
    private Button SortButton;

    @FXML
    void checkSortButton(ActionEvent event) {
        String order = OrderBox.getValue();
        if(order.equals(null) || order.equals("")) {
            return;
        }
        if (order.equals("Ascending")) {
            Actions.sortByAscendingPrice(carsList);
        } else {
            Actions.sortByDescendingPrice(carsList);
        }


    }
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
    void checkDeleteFiltrationList(ActionEvent event) {
        Manager manager = new Manager();
        carsList.clear();
        initCarsList(Manager.readTaxiFleet().getCarsList());
        double totalCost = manager.calculateTotalCarsCost(carsList);
        totalCostLabel.setText("Total cost: \n" + String.valueOf(totalCost));
        manufacture.setValue(null);
        colors.setValue(null);
        isHaveFilterByManufacture = false;
        isHaveFilterByColor = false;
    }

    @FXML
    public void initialize() throws ClassNotFoundException {
//        TaxiFleet taxiFleet = Manager.readTaxiFleet();
        Manager manager = new Manager();


        colors.getItems().addAll(Colors.values());
        colors.setOnAction(this::setColor);

        manufacture.getItems().addAll(Manufacture.values());
        manufacture.setOnAction(this::setManufacture);

        OrderBox.getItems().add("Ascending");
        OrderBox.getItems().add("Descending");

        TaxiFleet taxiFleet = Manager.readTaxiFleet();
        ColorColumn.setCellValueFactory(new PropertyValueFactory<Car, Colors>("color"));
        bodyTypeColumn.setCellValueFactory(new PropertyValueFactory<Car, BodyType>("bodyType"));
        manufactureColumn.setCellValueFactory(new PropertyValueFactory<Car, Manufacture>("manufacture"));
        engineColumn.setCellValueFactory(new PropertyValueFactory<Car, Engine>("engine"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, BigDecimal>("price"));
        yearOfIssueColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("yearOfIssue"));

        double totalCost = manager.calculateTotalCarsCost(taxiFleet.getCarsList());
        totalCostLabel.setText("Total cost: \n" + String.valueOf(totalCost));

        initCarsList(taxiFleet.getCarsList());
        carsTable.setItems(carsList);
    }

    private void setManufacture(ActionEvent actionEvent) {
        Manager manager = new Manager();
        if (isHaveFilterByManufacture && isHaveFilterByColor) {
            return;
        }
        if (isHaveFilterByManufacture) {
            carsList.clear();
            initCarsList(Manager.readTaxiFleet().getCarsList());
//            isHaveFilterByManufacture = false;
        }

        Manufacture selectedManufacture = manufacture.getValue();
        List<Car> cars = new ArrayList<>();
        for (Car car : carsList) {
            cars.add(car);
        }
        for(Car car : cars) {
            if (!((car.getManufacture()).equals(selectedManufacture))) {
//            }
//            else {
                carsList.remove(car);
            }
        }
        double totalCost = manager.calculateTotalCarsCost(carsList);
        totalCostLabel.setText("Total cost: \n" + String.valueOf(totalCost));
        isHaveFilterByManufacture = true;
    }

    private void setColor(ActionEvent actionEvent) {
        Manager manager = new Manager();
        if (isHaveFilterByManufacture && isHaveFilterByColor) {
            return;
        }
        if (isHaveFilterByColor) {
            carsList.clear();
            initCarsList(Manager.readTaxiFleet().getCarsList());
//            isHaveFilterByColor = false;
        }

        Colors selectedColor = colors.getValue();
        List<Car> cars = new ArrayList<>();
        for (Car car : carsList) {
            cars.add(car);
        }
        for(Car car : cars) {
            if (!((car.getColor()).equals(selectedColor))) {
//            }
//            else {
                carsList.remove(car);
            }
        }
        double totalCost = manager.calculateTotalCarsCost(carsList);
        totalCostLabel.setText("Total cost: \n" + String.valueOf(totalCost));
        isHaveFilterByColor = true;
    }

    private void initCarsList(List<Car> accountings) {
        for (Car accounting : accountings) {
            carsList.add(accounting);
        }
    }



    @FXML
    void checkAddCar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-car-view.fxml"));
        try {
            editCarsView.setCar(null);

            Parent root = loader.load();
            Stage stage = (Stage) (EditCarButton.getScene().getWindow());
            stage.setTitle("Edit Car!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void checkDeleteCar(ActionEvent event) {
        Manager manager = new Manager();
        TableView.TableViewSelectionModel<Car> selectionModel = carsTable.getSelectionModel();
        Car car = selectionModel.getSelectedItem();
        if(car == null) {
            return;
        }
        List<Car> cars = Manager.readTaxiFleet().getCarsList();
        int index = 0;
        for(Car el : cars) {
            if(Actions.equalsCars(car, el)) {
                break;
            }
            index++;
        }
        cars.remove(index);
        carsList.remove(car);
        Manager.saveTaxiFleet(new TaxiFleet(cars));
        carsTable.refresh();
        isHaveFilterByManufacture = false;
        isHaveFilterByColor = false;
        double totalCost = manager.calculateTotalCarsCost(carsList);
        totalCostLabel.setText("Total cost: \n" + String.valueOf(totalCost));
    }

    @FXML
    void checkEditCar(ActionEvent event) {
        TableView.TableViewSelectionModel<Car> selectionModel = carsTable.getSelectionModel();
        Car car = selectionModel.getSelectedItem();
        if (car == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-car-view.fxml"));
        try {
            editCarsView.setCar(car);
            Parent root = loader.load();
            Stage stage = (Stage) (EditCarButton.getScene().getWindow());


            stage.setTitle("Edit Car!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}