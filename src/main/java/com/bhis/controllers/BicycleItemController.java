package com.bhis.controllers;

import com.bhis.model.Bicycle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BicycleItemController extends GridPane implements Initializable
{
    @FXML
    public Label size;
    @FXML
    public Label color;

    @FXML
    public Label model;

    @FXML
    public Label type;

    @FXML
    private ImageView bicycleImage;

    @FXML
    private Label stockQty;
    @FXML
    private HBox hBox;

    private Bicycle bicycle;

    public BicycleItemController(Bicycle item)
    {
        this.bicycle = item;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/inventoryGridCellItem.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HBox getBox() {
        return hBox;
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setText(bicycle.getBicycleType().getBicycleType());
        size.setText(String.valueOf(bicycle.getBicycleSize()));
        model.setText(bicycle.getBicycleModel());
        color.setText(String.valueOf(bicycle.getBicycleColor()));
        stockQty.setText(bicycle.getBicycleQuantity() > 0 ? "In Stock" : "Out Of Stock");
        stockQty.setTextFill(bicycle.getBicycleQuantity() > 0 ? Color.GREEN : Color.RED);
        bicycleImage.setImage(new Image(getClass().getResourceAsStream(bicycle.getBicycleImage())));

    }

}