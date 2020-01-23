package com.bhis.controllers;

import com.bhis.service.BicycleService;
import com.bhis.service.CustomerService;
import com.bhis.service.HireRecordService;
import com.bhis.service.PaymentService;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox sideMenu;

    @FXML
    private JFXListView<Label> navBarList;

    @FXML
    private Pane mainBody;

    private int previousIndex = 0;

    private HireRecordService hireRecordService;
    private PaymentService paymentService;
    private CustomerService customerService;
    private BicycleService bicycleService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        navBarList.setOnMouseClicked(event -> {

            int index = navBarList.getSelectionModel().getSelectedIndex();
            switch (index) {
                case 0:
                    showView(index, "/view/hireRecordGridView.fxml");
                    break;
                case 1:
                    showView(index, "/view/paymentPage.fxml");
                    break;
                case 2:
                    showView(index, "/view/customerPage.fxml");
                    break;
                case 3:
                    showView(index, "/view/inventoryGridView.fxml");
                    break;
            }
        });

        navBarList.getSelectionModel().select(0);
        showView(0, "/view/hireRecordGridView.fxml");

    }

    private void showView(int index, String text) {
        if (index == previousIndex && !mainBody.getChildren().isEmpty()) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(text));
        if (index == 0) {
            HireRecordListController controller = new HireRecordListController();
            controller.initData(hireRecordService);
            loader.setController(controller);

        }
        previousIndex = index;
        try {
            Parent parent = loader.load();

            AnchorPane.setRightAnchor(parent, 0.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
            AnchorPane.setTopAnchor(parent, 0.0);
            AnchorPane.setBottomAnchor(parent, 0.0);

            if (!mainBody.getChildren().isEmpty()) {
                mainBody.getChildren().clear();
            }
            mainBody.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void initData(HireRecordService hService, PaymentService pService,
                         BicycleService bService) {
        hireRecordService = hService;
        paymentService = pService;
        bicycleService = bService;
    }
}
