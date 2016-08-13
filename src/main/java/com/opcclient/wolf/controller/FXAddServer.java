package com.opcclient.wolf.controller;


import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FXAddServer implements Initializable {

    private final String titleUPDATE = "OPC Server";

    private final String titleTags = "Tags in OPC Server";

    private final String pathUPDATE ="view/FXupdServerMini.fxml";

    private final String pathTags ="view/FXAddTags.fxml";

    private static OPCServer server;

    public static OPCServer getServer() {
        return server;
    }

    @FXML
    private ComboBox<String> fxOPCServerList;

    @FXML
    private TextField fxIPAddress;

    //table
    @FXML
    private TableView<OPCServer> fxTable;
    @FXML
    private TableColumn<OPCServer, Integer> fxID;
    @FXML
    private TableColumn<OPCServer, String> fxOPCServer;
    @FXML
    private TableColumn<OPCServer, String> fxAddress;
    @FXML
    private TableColumn<OPCServer, Integer> fxStatus;
    @FXML
    private TableColumn<OPCServer, Integer> fxTime;
    @FXML
    private TableColumn<OPCServer, String> fxClientName;
    @FXML
    private TableColumn<OPCServer, String> fxGroup;

    //metod
    public void onGetOPC(ActionEvent event){
        JeasyOPCClient opc;
        if(fxIPAddress.getText() != ""){
            opc = new JeasyOPCClient(fxIPAddress.getText());
            ObservableList<String> fruits = FXCollections.
                    observableArrayList(opc.getOPCServerList());
            fxOPCServerList.setItems(fruits);
        }
    }

    public void onAddOPC(ActionEvent event){
        OPCServer server = new OPCServer();
        if (fxIPAddress.getText() != "" && fxOPCServerList.getValue() != null){
            server.setOpcServer(fxOPCServerList.getValue());
            server.setServerName(fxIPAddress.getText());
            Factory.getInstance().getInsertDAO().addServerOPC(server);
        }
    }

    public void onGetBase(ActionEvent event){
        fxID.setCellValueFactory(new PropertyValueFactory<OPCServer, Integer>("id"));
        fxOPCServer.setCellValueFactory(new PropertyValueFactory<OPCServer, String>("opcServer"));
        fxAddress.setCellValueFactory(new PropertyValueFactory<OPCServer, String>("serverName"));
        fxStatus.setCellValueFactory(new PropertyValueFactory<OPCServer, Integer>("ServerStatus"));
        fxTime.setCellValueFactory(new PropertyValueFactory<OPCServer, Integer>("time"));
        fxClientName.setCellValueFactory(new PropertyValueFactory<OPCServer, String>("clientNameForOPC"));
        fxGroup.setCellValueFactory(new PropertyValueFactory<OPCServer, String>("nameGroup"));
        List<OPCServer> list = Factory.getInstance().getSelectDAO().getOPCServer();
        ObservableList<OPCServer> data = FXCollections.observableArrayList(list);
        fxTable.setItems(data);

    }

    public void onUpdate(ActionEvent event){
        if(fxTable.getSelectionModel().getSelectedItem() != null){
            server = (OPCServer) fxTable.getSelectionModel().getSelectedItem();
            openNewStage(event, pathUPDATE, titleUPDATE);
        }else OPCErrorCL.getInstance().errorSelect();
    }

    public void onDelete(ActionEvent event){
        if(fxTable.getSelectionModel().getSelectedItem() != null){
            server = (OPCServer) fxTable.getSelectionModel().getSelectedItem();
            Factory.getInstance().getDeleteDAO().deleteServerOPC(server);
        }else OPCErrorCL.getInstance().errorSelect();

    }

    public void onTags(ActionEvent event){
        if(fxTable.getSelectionModel().getSelectedItem() != null){
            server = (OPCServer) fxTable.getSelectionModel().getSelectedItem();
            openNewStage(event,pathTags,titleTags);
        }else OPCErrorCL.getInstance().errorSelect();
    }

    private void openNewStage(ActionEvent event, String view, String title){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(view));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            OPCErrorCL.getInstance().errorAny(e.toString());
        }
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
