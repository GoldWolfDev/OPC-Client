package com.opcclient.wolf.controller;


import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXupdMini implements Initializable {


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
    @FXML
    TextField fxf6;
    @FXML
    Label fxL0;

    public void onSave(ActionEvent event){
           OPCServer server = new OPCServer();
           server.setOpcServer(fxf1.getText());
           server.setServerName(fxf2.getText());
           server.setServerStatus(Integer.parseInt(fxf3.getText()));
           server.setTime(Integer.parseInt(fxf4.getText()));
           server.setClientNameForOPC(fxf5.getText());
           server.setNameGroup(fxf6.getText());
           server.setId(Integer.parseInt(fxL0.getText()));
           Factory.getInstance().getUpdateDAO().updServerOPC(server);
    }

    public void initialize(URL location, ResourceBundle resources) {
            fxf1.setText(FXAddServer.getServer().getOpcServer());
            fxf2.setText(FXAddServer.getServer().getServerName());
            fxf3.setText(String.valueOf(FXAddServer.getServer().isServerStatus()));
            fxf4.setText(String.valueOf(FXAddServer.getServer().getTime()));
            fxf5.setText(FXAddServer.getServer().getClientNameForOPC());
            fxf6.setText(FXAddServer.getServer().getNameGroup());
            fxL0.setText(String.valueOf(FXAddServer.getServer().getId()));
    }
}
