package com.opcclient.wolf.dao;

import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;

import java.util.List;

public interface SelectDAO {

    public List<OPCServer> getOPCServer();
}
