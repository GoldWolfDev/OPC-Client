package com.opcclient.wolf.dao;


import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class SelectDAOImpl implements SelectDAO{

    public List<OPCServer> getOPCServer() {
        List<OPCServer> list = new ArrayList<OPCServer>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            list = (List<OPCServer>)session.createCriteria(OPCServer.class).list();
        }catch (Exception ex){
            OPCErrorCL.getInstance().errorSQLConnect();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }
}
