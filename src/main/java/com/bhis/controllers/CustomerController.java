package com.bhis.controllers;

import com.bhis.database.CustomerDatabase;
import com.bhis.model.Customer;
import com.bhis.model.Customer.Gender;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private Label treeTableViewCount;

    @FXML
    private JFXButton treeTableViewAdd;

    @FXML
    private JFXButton treeTableViewRemove;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXTreeTableColumn<Customer, String> customerId;

    @FXML
    private JFXTreeTableColumn<Customer, String> firstName;

    @FXML
    private JFXTreeTableColumn<Customer, String> lastName;

    @FXML
    private JFXTreeTableColumn<Customer, Gender> gender;

    @FXML
    private JFXTreeTableColumn<Customer, String> emailAddress;

    @FXML
    private JFXTreeTableColumn<Customer, String> homeAddress;

    @FXML
    private JFXTreeTableView<Customer> customerTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addColumns(); // 1st
        addTableData(); // 2nd
        addSearchFunctionality(); // 3rd
    }

    private void addColumns() {

        customerId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> {
            if (customerId.validateValue(param)) return param.getValue().getValue().customerIdProperty();
            else return customerId.getComputedValue(param);
        });

        firstName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> {
            if (firstName.validateValue(param)) return param.getValue().getValue().firstNameProperty();
            else return firstName.getComputedValue(param);
        });

        lastName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> {
            if (lastName.validateValue(param)) return param.getValue().getValue().lastNameProperty();
            else return lastName.getComputedValue(param);
        });

        gender.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, Gender> param) -> {
            if (gender.validateValue(param)) return param.getValue().getValue().genderProperty();
            else return gender.getComputedValue(param);
        });

        emailAddress.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> {
            if (emailAddress.validateValue(param)) return param.getValue().getValue().emailAddressProperty();
            else return emailAddress.getComputedValue(param);
        });

        homeAddress.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> {
            if (homeAddress.validateValue(param)) return param.getValue().getValue().homeAddressProperty();
            else return homeAddress.getComputedValue(param);
        });
    }


    private void addTableData() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.addAll(CustomerDatabase.getInstance().getCustomerList());

        final TreeItem<Customer> root = new RecursiveTreeItem<>(customers, RecursiveTreeObject::getChildren);

        customerTable.setRoot(root);
        customerTable.setShowRoot(false);
        customerTable.setEditable(true);
    }

    private void addSearchFunctionality() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> customerTable.setPredicate(customer ->
                customer.getValue().customerIdProperty().get().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().firstNameProperty().get().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().lastNameProperty().get().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().genderProperty().getValue().name().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().emailAddressProperty().get().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().homeAddressProperty().get().toLowerCase().contains(newValue.toLowerCase())
        ));


        treeTableViewCount.textProperty().bind(Bindings.createStringBinding(() -> "(" + customerTable.getCurrentItemsCount() + ")",
                customerTable.currentItemsCountProperty()
        ));
    }
}
