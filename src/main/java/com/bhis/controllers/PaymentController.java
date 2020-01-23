package com.bhis.controllers;

import com.bhis.database.PaymentDatabase;
import com.bhis.model.Payment;
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

public class PaymentController implements Initializable {

    @FXML
    private Label treeTableViewCount;

    @FXML
    private JFXButton treeTableViewAdd;

    @FXML
    private JFXButton treeTableViewRemove;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXTreeTableView<Payment> paymentTable;

    @FXML
    private JFXTreeTableColumn<Payment, String> paymentId;

    @FXML
    private JFXTreeTableColumn<Payment, String> customerId;

    @FXML
    private JFXTreeTableColumn<Payment, String> customerName;

    @FXML
    private JFXTreeTableColumn<Payment, String> bicycleId;

    @FXML
    private JFXTreeTableColumn<Payment, String> bicycleType;

    @FXML
    private JFXTreeTableColumn<Payment, Integer> hoursLate;

    @FXML
    private JFXTreeTableColumn<Payment, Double> amountHoursLate;

    @FXML
    private JFXTreeTableColumn<Payment, String> hireDuration;

    @FXML
    private JFXTreeTableColumn<Payment, String> payDate;

    @FXML
    private JFXTreeTableColumn<Payment, Double> amountDeposit;

    @FXML
    private JFXTreeTableColumn<Payment, Double> amountDue;

    @FXML
    private JFXTreeTableColumn<Payment, Double> totalPaid;

    @FXML
    private JFXTreeTableColumn<Payment, Double> totalPaidNoDeposit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addColumns();
        addTableData();
        addSearchFunctionality();
    }

    private void addColumns() {

        paymentId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (paymentId.validateValue(param)) return param.getValue().getValue().paymentIdProperty();
            else return paymentId.getComputedValue(param);
        });


        customerId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (customerId.validateValue(param)) return param.getValue().getValue().customerIdProperty();
            else return customerId.getComputedValue(param);
        });

        customerName.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (customerName.validateValue(param)) return param.getValue().getValue().customerNameProperty();
            else return customerName.getComputedValue(param);
        });

        bicycleId.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (bicycleId.validateValue(param)) return param.getValue().getValue().bicycleIdProperty();
            else return bicycleId.getComputedValue(param);
        });


        bicycleType.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (bicycleType.validateValue(param)) return param.getValue().getValue().bicycleTypeProperty();
            else return bicycleType.getComputedValue(param);
        });

        hoursLate.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Integer> param) -> {
            if (hoursLate.validateValue(param)) return param.getValue().getValue().hoursLateProperty().asObject();
            else return hoursLate.getComputedValue(param);
        });

        amountHoursLate.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Double> param) -> {
            if (amountHoursLate.validateValue(param))
                return param.getValue().getValue().amountHoursLateProperty().asObject();
            else return amountHoursLate.getComputedValue(param);
        });


        hireDuration.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (hireDuration.validateValue(param)) return param.getValue().getValue().hireDurationProperty();
            else return hireDuration.getComputedValue(param);
        });

        payDate.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> {
            if (payDate.validateValue(param)) return param.getValue().getValue().payDateProperty();
            else return payDate.getComputedValue(param);
        });

        amountDeposit.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Double> param) -> {
            if (amountDeposit.validateValue(param)) return param.getValue().getValue().depositProperty().asObject();
            else return amountDeposit.getComputedValue(param);
        });

        amountDue.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Double> param) -> {
            if (amountDue.validateValue(param)) return param.getValue().getValue().amountDueProperty().asObject();
            else return amountDue.getComputedValue(param);
        });

        totalPaid.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Double> param) -> {
            if (totalPaid.validateValue(param)) return param.getValue().getValue().totalAmountPaidProperty();
            else return totalPaid.getComputedValue(param);
        });

        totalPaidNoDeposit.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, Double> param) -> {
            if (totalPaidNoDeposit.validateValue(param))
                return param.getValue().getValue().totalAmountPaidNoDepositProperty();
            else return totalPaidNoDeposit.getComputedValue(param);
        });
    }

    private void addTableData() {
        ObservableList<Payment> payments = FXCollections.observableArrayList();
        payments.addAll(PaymentDatabase.getInstance().getPaymentList());

        final TreeItem<Payment> root = new RecursiveTreeItem<>(payments, RecursiveTreeObject::getChildren);

        paymentTable.setRoot(root);
        paymentTable.setShowRoot(false);
        paymentTable.setEditable(true);
    }

    private void addSearchFunctionality() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> paymentTable.setPredicate(customer ->
                customer.getValue().paymentIdProperty().get().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().customerNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().bicycleIdProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().customerIdProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().bicycleTypeProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                        || customer.getValue().payDateProperty().getValue().equals((newValue.toLowerCase()))
        ));


        treeTableViewCount.textProperty().bind(Bindings.createStringBinding(() -> "(" + paymentTable.getCurrentItemsCount() + ")",
                paymentTable.currentItemsCountProperty()
        ));
    }
}
