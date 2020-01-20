package com.bhis.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        navBarList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Label label = navBarList.getSelectionModel()
                                    .getSelectedItem();
                switch (label.getText()){
                    case "INVENTORY":
                        showInventoryView(label.getText());
                        break;

                }
            }
        });

        navBarList.getSelectionModel().selectLast();
        showInventoryView("INVENTORY");

    }

    private void showInventoryView(String text) {
        if(navBarList.getSelectionModel().getSelectedItem().getText().equals(text) && mainBody.getChildren().size() > 0){
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/inventoryGridView.fxml"));

        try {
            Parent parent = loader.load();
            AnchorPane.setRightAnchor(parent, 0.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
            AnchorPane.setTopAnchor(parent, 0.0);
            AnchorPane.setBottomAnchor(parent, 0.0);

            mainBody.getChildren().removeAll();
            mainBody.getChildren().add(parent);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
