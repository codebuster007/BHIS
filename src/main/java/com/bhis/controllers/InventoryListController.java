package com.bhis.controllers;

import com.bhis.database.BicycleDatabase;
import com.bhis.model.Bicycle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.GridView;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class InventoryListController implements Initializable {

    @FXML
    public GridView<Bicycle> bicycleGridView;
    @FXML
    public AnchorPane listAnchorPane;

    private Set<String> stringSet;

    private ObservableList<Bicycle> observableList = FXCollections.observableArrayList();


    public void setListView()
    {
        observableList.setAll(BicycleDatabase.getInstance().getBicycleList());
        bicycleGridView.setItems(observableList);
        bicycleGridView.setCellFactory(param -> new GridViewCell());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setRightAnchor(listAnchorPane, 0.0);
        AnchorPane.setLeftAnchor(listAnchorPane, 0.0);
        AnchorPane.setBottomAnchor(listAnchorPane, 0.0);
        AnchorPane.setTopAnchor(listAnchorPane, 0.0);
        stringSet = new HashSet<>();
        setListView();


    }
}
