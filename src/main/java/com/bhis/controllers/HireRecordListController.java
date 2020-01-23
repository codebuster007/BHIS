package com.bhis.controllers;

import com.bhis.database.HireRecordDatabase;
import com.bhis.model.HireRecord;
import com.bhis.service.HireRecordService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.GridView;

import java.net.URL;
import java.util.*;

public class HireRecordListController implements Initializable {

    @FXML
    public GridView<HireRecord> hireRecordGridView;

    @FXML
    public JFXTextField searchField;

    @FXML
    public JFXButton addNewHire;

    @FXML
    public AnchorPane listAnchorPane;

    private Set<String> stringSet;

    private ObservableList<HireRecord> observableList;

    private HireRecordService service;

    public HireRecordListController() {
        observableList = FXCollections.observableArrayList();
    }

    public void setListView(List<HireRecord> list) {

        observableList.setAll(list);
        hireRecordGridView.setItems(observableList);
        hireRecordGridView.setCellFactory(param -> new HireRecordItemGridViewCell(service));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setRightAnchor(listAnchorPane, 0.0);
        AnchorPane.setLeftAnchor(listAnchorPane, 0.0);
        AnchorPane.setBottomAnchor(listAnchorPane, 0.0);
        AnchorPane.setTopAnchor(listAnchorPane, 0.0);
        stringSet = new HashSet<>();
        setListView(HireRecordDatabase.getInstance().getHireRecordList());

        // Search Functionality added
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            List<HireRecord> hireRecords = new ArrayList<>();

            // TODO: costly operation needs refactor
            HireRecordDatabase.getInstance().getHireRecordList().forEach(hireRecord -> {
                if (hireRecord.getBicycleHired().getBicycleNo().toLowerCase().contains(newValue.toLowerCase())
                        || hireRecord.getHireId().toLowerCase().contains(newValue.toLowerCase())
                        || hireRecord.getCustomerHiring().getCustomerId().toLowerCase().contains(newValue.toLowerCase())
                        || hireRecord.getCustomerHiring().getFirstName().toLowerCase().contains(newValue.toLowerCase())
                        || hireRecord.getCustomerHiring().getLastName().toLowerCase().contains(newValue.toLowerCase())) {
                    hireRecords.add(hireRecord);
                }
            });
            setListView(hireRecords);

            if (newValue.isEmpty()) {
                setListView(HireRecordDatabase.getInstance().getHireRecordList());
            }
        });

        // Add new Hire functionality
        addNewHire.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
    }

    public void initData(HireRecordService recordService) {
        service = recordService;

    }
}
