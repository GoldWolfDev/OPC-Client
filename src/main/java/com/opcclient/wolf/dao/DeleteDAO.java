package com.opcclient.wolf.dao;

import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;

/**
 * Created by RafikovAR on 10.08.2016.
 */
public interface DeleteDAO {

    public void deleteServerOPC(OPCServer opcServer);

    public void deleteTags(BaseOPC opc);
}
