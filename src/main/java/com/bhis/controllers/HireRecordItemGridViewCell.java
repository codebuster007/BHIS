package com.bhis.controllers;

import com.bhis.model.HireRecord;
import com.bhis.service.HireRecordService;
import javafx.scene.control.ContentDisplay;
import org.controlsfx.control.GridCell;

public class HireRecordItemGridViewCell extends GridCell<HireRecord> {
    private HireRecordService hireRecordService;

    HireRecordItemGridViewCell(HireRecordService service) {
        hireRecordService = service;
    }

    @Override
    protected void updateItem(HireRecord item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null) {
            HireRecordItemController itemController = new HireRecordItemController(item, hireRecordService);
            setGraphic(itemController.getRoot());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
