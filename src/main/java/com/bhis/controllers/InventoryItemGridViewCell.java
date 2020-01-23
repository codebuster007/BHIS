package com.bhis.controllers;

import com.bhis.model.Bicycle;
import javafx.scene.control.ContentDisplay;
import org.controlsfx.control.GridCell;

public class InventoryItemGridViewCell extends GridCell<Bicycle> {

    @Override
    protected void updateItem(Bicycle item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            InventoryItemController inventoryItemController = new InventoryItemController(item);
            setGraphic(inventoryItemController.getRoot());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
