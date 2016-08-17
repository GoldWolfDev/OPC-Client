package com.opcclient.wolf.controller;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.ItemTags;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.Factory;
import com.opcclient.wolf.util.HibernateUtil;
import com.sun.javafx.tk.quantum.QuantumToolkit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FXAddTags implements Initializable {

    private JeasyOPCClient opcClient = new JeasyOPCClient();

    private OPCServer opc = FXAddServer.getServer();

    private final String path = "view/FXupdTagsMini.fxml";

    private final String title ="Update Tag";

    private static BaseOPC baseopc;


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

    //table Tags
    @FXML
    private TableColumn<BaseOPC, Integer> fxid;
    @FXML
    private TableColumn<BaseOPC, String> fxObjID;
    @FXML
    private TableColumn<BaseOPC, String> fxItemName;
    @FXML
    private TableColumn<BaseOPC, Integer> fxPropType;
    @FXML
    private TableColumn<BaseOPC, Integer> fxEvent;
    @FXML
    private TableColumn<BaseOPC, Integer> fxObjType;
    @FXML
    private TableView<BaseOPC> fxTags;




    //metods

    public static BaseOPC getBaseopc() {
        return baseopc;
    }

    private void openNewStage(ActionEvent event, String view, String title){
        Stage stage = new Stage();
        baseopc = fxTags.getSelectionModel().getSelectedItem();
        try {
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
        updTableTags();
        firstGetBranch();
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
            if (list.size()>0){
                for(String messa : list){
                    fxNode.getSelectionModel().getSelectedItem().getChildren().add(new TreeItem<String>(getTreeV+"."+messa));
                }
            }else  OPCErrorCL.getInstance().errorAny("This not branch more");

        }else OPCErrorCL.getInstance().errorSelect();
    }


    public void onTagsDelete(ActionEvent event) {
        if (fxTable.getSelectionModel().getSelectedItem() != null){
            fxTable.getItems().remove(fxTable.getSelectionModel().getSelectedIndex());
        }
    }

    public void onSave(ActionEvent event) {
        BaseOPC baseOPC;
        for (int i=0; fxTable.getItems().size() > i; i++){
            baseOPC = new BaseOPC();
            baseOPC.setOPCServerID(opc);
            baseOPC.setItemName(fxTag.getCellObservableValue(i).getValue());
            Factory.getInstance().getInsertDAO().addItems(baseOPC);
        }
        updTableTags();
    }

    public void onGetTagsFromDB(ActionEvent event) {
        updTableTags();
    }

    public void onDeleteTags(ActionEvent event) {
       Factory.getInstance().getDeleteDAO().deleteTags(fxTags.getSelectionModel().getSelectedItem());
       updTableTags();
    }

    public void onUpdateTags(ActionEvent event) {
        openNewStage(event, path, title);
    }

    private void updTableTags(){
        fxid.setCellValueFactory(new PropertyValueFactory<BaseOPC, Integer>("id"));
        fxObjID.setCellValueFactory(new PropertyValueFactory<BaseOPC, String>("objID"));
        fxItemName.setCellValueFactory(new PropertyValueFactory<BaseOPC, String>("itemName"));
        fxPropType.setCellValueFactory(new PropertyValueFactory<BaseOPC, Integer>("propType"));
        fxObjType.setCellValueFactory(new PropertyValueFactory<BaseOPC, Integer>("objType"));
        fxEvent.setCellValueFactory(new PropertyValueFactory<BaseOPC, Integer>("eventType"));
        List<BaseOPC> list = Factory.getInstance().getSelectDAO().getOPCTags(opc.getId());
        ObservableList<BaseOPC> data = FXCollections.observableArrayList(list);
        fxTags.setItems(data);
    }

    private void firstGetBranch(){
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
}
