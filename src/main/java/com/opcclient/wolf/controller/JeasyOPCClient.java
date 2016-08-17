package com.opcclient.wolf.controller;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.ItemTags;
import com.opcclient.wolf.model.OPCServer;
import javafish.clients.opc.browser.JOpcBrowser;
import javafish.clients.opc.exception.*;

import java.util.ArrayList;
import java.util.List;

public class JeasyOPCClient {

    private List<String> listOPCServer = null;

    private List<String> listTree = null;

    private List<ItemTags> listTags = null;

    private String ipAddress;

    public JeasyOPCClient(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public JeasyOPCClient(){}

    //получение OPC Servers from IP Address
    public List<String> getOPCServerList() {
        //инициализация переменных
        String[] opcServers = new String[0];
        listOPCServer = new ArrayList<String>();
        //коннест к компьютеру и забор тегов.
        JOpcBrowser.coInitialize();
        try {
            opcServers = JOpcBrowser.getOpcServers(ipAddress);
            for (String server : opcServers) {
                listOPCServer.add(server);
            }
        } catch (HostException e) {
            OPCErrorCL.getInstance().errorServer1();
        } catch (NotFoundServersException e) {
            OPCErrorCL.getInstance().errorServer2();
        }
        JOpcBrowser.coUninitialize();
        //возврат данных
        return listOPCServer;
    }
    //получение дерева
    public List<String> getTree(OPCServer opcServer, String branch) {
        listTree = new ArrayList<String>();
        JOpcBrowser jbrowser = new JOpcBrowser(opcServer.getServerName(),
                opcServer.getOpcServer(), "WolfCl");
        try {
            JOpcBrowser.coInitialize();
            jbrowser.connect();
            String[] listBranch = jbrowser.getOpcBranch(branch);
            for (String mass : listBranch) {
                listTree.add(mass);
            }
        } catch (ConnectivityException e) {
            OPCErrorCL.getInstance().errorAny("The connection to the OPC Server has failed: "+ opcServer.getServerName());
        }
        catch (UnableIBrowseException e) {
            OPCErrorCL.getInstance().errorAny(e.toString());
        } catch (UnableBrowseBranchException e) {
            OPCErrorCL.getInstance().errorAny(e.toString());
        }finally {
            JOpcBrowser.coUninitialize();
        }

        return listTree;
    }
    //получение списка
    public List<ItemTags> getListTags(OPCServer opcServer, String branch){
        listTags = new ArrayList<ItemTags>();
        JOpcBrowser.coInitialize();
        JOpcBrowser jbrowser = new JOpcBrowser(opcServer.getServerName(),
                opcServer.getOpcServer(), "WolfCl");
        try {
            jbrowser.connect();
            String[] items = jbrowser.getOpcItems(branch, false);
            if (items != null) {
                for (String item : items) {
                    listTags.add(parserItems(item));
                }
            }
        } catch (ConnectivityException e) {
            e.printStackTrace();
        } catch (UnableAddItemException e) {
            e.printStackTrace();
        } catch (UnableBrowseLeafException e) {
            e.printStackTrace();
        } catch (UnableAddGroupException e) {
            e.printStackTrace();
        } catch (UnableIBrowseException e) {
            e.printStackTrace();
        }
        JOpcBrowser.coUninitialize();
        return listTags;
    }

    private ItemTags parserItems(String mass){
        String[] lem = mass.split("; ");
        ItemTags tag = new ItemTags();
        tag.setNameTags(lem[0].toString());
        tag.setTypeTags(lem[1].toString());
        tag.setTags(lem[2].toString());
        return tag;
    }


}