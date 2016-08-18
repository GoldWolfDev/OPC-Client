package com.opcclient.wolf.dao;

import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;

public interface UpdateDAO {

    public void updServerOPC(OPCServer opcServer);

    public void updOPCTags(BaseOPC opc);
}
