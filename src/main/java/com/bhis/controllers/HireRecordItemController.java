package com.bhis.controllers;

import com.bhis.model.HireRecord;
import com.bhis.service.HireRecordService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static com.bhis.model.HireRecord.HireStatus.COMPLETED;

public class HireRecordItemController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label startDate;

    @FXML
    private Label endDate;

    @FXML
    private JFXProgressBar hireProgress;

    @FXML
    private Label totalDue;

    @FXML
    private Label hireDuration;

    @FXML
    private Label hireStatus;

    @FXML
    private Label hireId;

    @FXML
    private Label bikeId;

    @FXML
    private Label bikeMake;

    @FXML
    private Label bikeType;

    @FXML
    private JFXButton moreBtn;

    @FXML
    private Label custId;

    @FXML
    private Label custName;

    @FXML
    private Label custEmail;

    @FXML
    private Label custGender;

    @FXML
    private JFXCheckBox bikeRetured;

    @FXML
    private JFXCheckBox depositRefunded;

    private HireRecord hireRecord;
    private HireRecordService hireRecordService;

    public HireRecordItemController(HireRecord item, HireRecordService hireRecordService) {
        this.hireRecordService = hireRecordService;
        this.hireRecord = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/hireRecordItem.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCardData();

    }

    private void setCardData() {
        hireId.setText(hireRecord.getHireId());
        startDate.setText(getFormattedDate(hireRecord.getStartDate()));
        endDate.setText(getFormattedDate(hireRecord.getReturnDate()));
        hireDuration.setText(hireRecord.getHireDuration());
        hireStatus.setText(hireRecord.getHireStatus().name());
        hireStatus.setTextFill(hireRecord.getHireStatus().equals(COMPLETED) ? Color.GREEN : Color.rgb(6, 167, 249));
        hireProgress.setProgress(hireRecord.getHireStatus().equals(COMPLETED) ? 100.0 : JFXProgressBar.INDETERMINATE_PROGRESS);

        totalDue.setText("$" + hireRecord.getAmountDue());
        bikeId.setText(hireRecord.getBicycleHired().getBicycleNo());
        bikeMake.setText(hireRecord.getBicycleHired().getBicycleMake());
        bikeType.setText(hireRecord.getBicycleHired().getBicycleType().getBicycleType());

        custId.setText(hireRecord.getCustomerHiring().getCustomerId());
        custName.setText(hireRecord.getCustomerHiring().getFirstName() +
                " " + hireRecord.getCustomerHiring().getLastName());
        custEmail.setText(hireRecord.getCustomerHiring().getEmailAddress());
        custGender.setText(hireRecord.getCustomerHiring().getGender().name());

        bikeRetured.setSelected(hireRecord.isBicycleReturned());
        bikeRetured.setDisable(hireRecord.isBicycleReturned());
        bikeRetured.setOnMouseClicked(event -> {
            hireRecord.setHireStatus(COMPLETED);
            hireProgress.setProgress(hireRecord.getHireStatus().equals(COMPLETED) ? 100.0 : JFXProgressBar.INDETERMINATE_PROGRESS);
            hireStatus.setTextFill(hireRecord.getHireStatus().equals(COMPLETED) ? Color.GREEN : Color.rgb(6, 167, 249));
            hireStatus.setText(hireRecord.getHireStatus().name());
            hireRecord.setBicycleReturned(bikeRetured.isSelected());
            bikeRetured.setDisable(hireRecord.isBicycleReturned());
            depositRefunded.setDisable(false);
            hireRecordService.updateHireRecord(hireRecord);
        });

        depositRefunded.setSelected(hireRecord.isDepositRefunded());
        depositRefunded.setDisable(depositRefunded.isSelected() == hireRecord.isBicycleReturned());
        depositRefunded.setOnMouseClicked(event -> {
            hireRecord.setDepositRefunded(depositRefunded.isSelected());
            depositRefunded.setDisable(hireRecord.isDepositRefunded());
            hireRecordService.updateHireRecord(hireRecord);
        });

    }

    public BorderPane getRoot() {

        return borderPane;
    }

    private String getFormattedDate(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a ").format(localDateTime);
    }
}
