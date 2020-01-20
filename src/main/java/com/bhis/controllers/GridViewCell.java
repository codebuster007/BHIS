package com.bhis.controllers;

import com.bhis.model.Bicycle;
import javafx.scene.control.ContentDisplay;
import org.controlsfx.control.GridCell;

public class GridViewCell extends GridCell<Bicycle> {

    @Override
    protected void updateItem(Bicycle item, boolean empty) {
        super.updateItem(item, empty);

        if(item != null){
            BicycleItemController bicycleItemController = new BicycleItemController(item);
            setGraphic(bicycleItemController.getBox());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
