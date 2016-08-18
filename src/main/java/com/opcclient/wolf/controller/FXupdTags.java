package com.opcclient.wolf.controller;


import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXupdTags implements Initializable {

    @FXML
    TextField fxf1;
    @FXML
    TextField fxf2;
    @FXML
    TextField fxf3;
    @FXML
    TextField fxf4;
    @FXML
    TextField fxf5;

    public void onSave(ActionEvent event){
           BaseOPC server = new BaseOPC();
           server.setObjID(fxf1.getText());
           server.setItemName(fxf2.getText());
           server.setPropType(Integer.parseInt(fxf3.getText()));
           server.setEventType(Integer.parseInt(fxf4.getText()));
           server.setObjType(Integer.parseInt(fxf5.getText()));
           server.setId(FXAddTags.getBaseopc().getId());
           Factory.getInstance().getUpdateDAO().updOPCTags(server);
    }

    public void initialize(URL location, ResourceBundle resources) {
            fxf1.setText(FXAddTags.getBaseopc().getObjID());
            fxf2.setText(FXAddTags.getBaseopc().getItemName());
            fxf3.setText(String.valueOf(FXAddTags.getBaseopc().getPropType()));
            fxf4.setText(String.valueOf(FXAddTags.getBaseopc().getEventType()));
            fxf5.setText(String.valueOf(FXAddTags.getBaseopc().getObjType()));
    }
}
