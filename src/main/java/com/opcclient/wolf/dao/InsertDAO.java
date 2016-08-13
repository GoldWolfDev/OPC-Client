package com.opcclient.wolf.dao;


import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;

import java.util.List;

public interface InsertDAO {

    public void addServerOPC(OPCServer addOPCServer);

    public void addItems(List<BaseOPC> opcItem);

}
