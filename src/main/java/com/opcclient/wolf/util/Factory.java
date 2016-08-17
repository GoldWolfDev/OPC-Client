package com.opcclient.wolf.util;


import com.opcclient.wolf.dao.*;

public class Factory {

    private static Factory instance =null;

    private InsertDAO insertDAO = null;

    private SelectDAO selectDAO = null;

    private UpdateDAO updateDAO = null;

    private DeleteDAO deleteDAO = null;


    private Factory(){}

    public static Factory getInstance() {
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public InsertDAO getInsertDAO() {
        if (insertDAO == null){
            insertDAO = new InsertDAOImpl();
        }
        return insertDAO;
    }

    public DeleteDAO getDeleteDAO() {
        if (deleteDAO == null){
            deleteDAO = new DeleteDAOImpl();
        }
        return deleteDAO;
    }

    public SelectDAO getSelectDAO() {
        if (selectDAO == null){
            selectDAO = new SelectDAOImpl();
        }
        return selectDAO;
    }

    public UpdateDAO getUpdateDAO() {
        if (updateDAO == null){
            updateDAO = new UpdateDAOImpl();
        }
        return updateDAO;
    }
}
