package com.opcclient.wolf.controller;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.ItemTags;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.Factory;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FXAddTags implements Initializable {

    private JeasyOPCClient opcClient = new JeasyOPCClient();

    private OPCServer opc = FXAddServer.getServer();

    //table JOPCBrouwser
    @FXML
    private TableView<ItemTags> fxTable;
    @FXML
    private TreeView<String> fxNode;
    @FXML
    private TableColumn<ItemTags, Integer> fxName;
    @FXML
    private TableColumn<ItemTags, String> fxTag;
    @FXML
    private TableColumn<ItemTags, String> fxType;

    private TreeItem<String> item;


    //metods

    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<String> list = opcClient.getTree(opc,"");
            TreeItem<String> item = new TreeItem<String>(opc.getOpcServer());
            item.setExpanded(true);
            for (String branch : list){
                item.getChildren().addAll(
                        new TreeItem<String>(branch)
                );
            }
            fxNode.setRoot(item);
        }catch (Exception e){
            OPCErrorCL.getInstance().errorAny(e.toString());
        }
    }

    public void onGetTags(ActionEvent event) {
        if (fxNode.getSelectionModel().getSelectedItem().getValue() != null){
            fxName.setCellValueFactory(new PropertyValueFactory<ItemTags, Integer>("nameTags"));
            fxTag.setCellValueFactory(new PropertyValueFactory<ItemTags, String>("tags"));
            fxType.setCellValueFactory(new PropertyValueFactory<ItemTags, String>("typeTags"));
            List<ItemTags> list = opcClient.getListTags(opc, fxNode.getSelectionModel().getSelectedItem().getValue());
            ObservableList<ItemTags> data = FXCollections.observableArrayList(list);
            fxTable.setItems(data);
        }else OPCErrorCL.getInstance().errorSelect();
    }

    public void onGetTree(ActionEvent event){
        if (fxNode.getSelectionModel().getSelectedItem().getValue() != null){
            String getTreeV = fxNode.getSelectionModel().getSelectedItem().getValue();
            List<String> list = opcClient.getTree(opc,getTreeV);
            for(String messa : list){
                fxNode.getSelectionModel().getSelectedItem().getChildren().add(new TreeItem<String>(getTreeV+"."+messa));
            }
        }else OPCErrorCL.getInstance().errorSelect();
    }
}
