package com.example.laba_5_sem_2_javafx;

import com.example.laba_5_sem_2_javafx.entity.engine.Engine;
import com.example.laba_5_sem_2_javafx.entity.taxiFleet.TaxiFleet;
import com.example.laba_5_sem_2_javafx.enums.BodyType;
import com.example.laba_5_sem_2_javafx.enums.Colors;
import com.example.laba_5_sem_2_javafx.enums.EngineType;
import com.example.laba_5_sem_2_javafx.enums.Manufacture;
import com.example.laba_5_sem_2_javafx.logic.Actions;
import com.example.laba_5_sem_2_javafx.logic.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class editCarsView {

    private static Car car = null;

    public static void setCar(Car newCar) {
        car = newCar;
    }

    @FXML
    private ChoiceBox<BodyType> bodyTypeBox;

    @FXML
    private Label bodyTypeLbl;

    @FXML
    private Button buttonBack;

    @FXML
    private ChoiceBox<Colors> colorBox;

    @FXML
    private Label colorLbl;

    @FXML
    private Label engineLbl;

    @FXML
    private ChoiceBox<EngineType> engineTypeBox;

    @FXML
    private Label manufactureLbl;

    @FXML
    private ChoiceBox<Manufacture> manufactureBox;

    @FXML
    private TextField powerField;

    @FXML
    private TextField priceField;

    @FXML
    private Label priceLbl;

    @FXML
    private TextField volumeField;

    @FXML
    private TextField yearOfIssueField;

    @FXML
    private Label yearOfIssueLbl;

    @FXML
    private Button editCarButton;

    @FXML
    private Label errorLbl;

    @FXML
    void checkButtonBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("taxis-admin-view.fxml"));
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
    public void initialize() throws ClassNotFoundException {

        manufactureBox.getItems().addAll(Manufacture.values());

        bodyTypeBox.getItems().addAll(BodyType.values());

        colorBox.getItems().addAll(Colors.values());

        engineTypeBox.getItems().addAll(EngineType.values());

        if(car != null) {
            manufactureLbl.setText(car.getManufacture().toString());
            colorLbl.setText(car.getColor().toString());
            bodyTypeLbl.setText(car.getBodyType().toString());
            priceLbl.setText(String.valueOf(car.getPrice()));
            engineLbl.setText(new Engine(
                    car.getEngine().getPower(),
                    car.getEngine().getVolume(),
                    EngineType.DIESEL).toString());
            yearOfIssueLbl.setText(String.valueOf(car.getYearOfIssue()));

        }
    }

    @FXML
    void editCarButtonCheck(ActionEvent event) {

        TaxiFleet taxiFleet = Manager.readTaxiFleet();

        Manufacture selectedManufacture = manufactureBox.getValue();
        Colors selectedColors = colorBox.getValue();
        BodyType selectedBodyType = bodyTypeBox.getValue();
        EngineType selectedEngineType = engineTypeBox.getValue();
        String stringVolume = volumeField.getText();
        String stringYear = yearOfIssueField.getText();
        String stringPrice = priceField.getText();
        String stringPower = powerField.getText();


        if (car == null) {
            if (stringPrice.isEmpty() ||
                    stringPower.isEmpty() ||
                    stringVolume.isEmpty() ||
                    selectedManufacture == null ||
                    stringYear.isEmpty() ||
                    selectedBodyType == null ||
                    selectedEngineType == null ||
                    selectedColors == null) {

                return;
            }
            try {


                int selectedYear = Integer.parseInt(stringYear);
                double selectedPrice = Double.parseDouble(stringPrice);
                int selectedPower = Integer.parseInt(stringPower);
                double selectedVolume = Double.parseDouble(stringVolume);

            taxiFleet.addCarToList(
                    new Car(
                            new Engine(selectedPower, selectedVolume, selectedEngineType),
                            selectedBodyType,
                            selectedColors,
                            selectedManufacture,
                            selectedPrice,
                            selectedYear));
            }catch (NumberFormatException e) {
                return;
            }
        } else {
            List<Car> cars = taxiFleet.getCarsList();
            int index = 0;

            for(Car oneCar : cars) {
                if (Actions.equalsCars(oneCar, car)) {
//                    oneCar = new Car(
//                            new Engine(
//                                    stringPower.isEmpty()?oneCar.getEngine().getPower() : Integer.parseInt(stringPower),
//                                    stringVolume.isEmpty()?oneCar.getEngine().getVolume():Double.parseDouble(stringVolume),
//                                    selectedEngineType == null?EngineType.DIESEL:selectedEngineType),
//                            selectedBodyType,
//                            selectedColors,
//                            selectedManufacture,
//                            Double.parseDouble(stringPrice),
//                            Integer.parseInt(stringYear));
                    break;
                }
                index++;
            }
            try {


                cars.get(index).setColor(selectedColors);

                cars.get(index).setEngine(new Engine(
                        stringPower.isEmpty() ? cars.get(index).getEngine().getPower() : Integer.parseInt(stringPower),
                        stringVolume.isEmpty() ? cars.get(index).getEngine().getVolume() : Double.parseDouble(stringVolume),
                        selectedEngineType == null ? EngineType.DIESEL : selectedEngineType));

                cars.get(index).setBodyType(selectedBodyType == null ? cars.get(index).getBodyType() : selectedBodyType);

                cars.get(index).setManufacture(selectedManufacture == null ? cars.get(index).getManufacture() : selectedManufacture);

                cars.get(index).setPrice(stringPrice.isEmpty() ? cars.get(index).getPrice() : Double.parseDouble(stringPrice));

                cars.get(index).setYearOfIssue(stringYear.isEmpty() ? cars.get(index).getYearOfIssue() : Integer.parseInt(stringYear));
            }catch (NumberFormatException e) {
                return;
            }
        }

        Manager.saveTaxiFleet(taxiFleet);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("taxis-admin-view.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) (buttonBack.getScene().getWindow());
            stage.setTitle("Menu!");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        car = null;
    }
}