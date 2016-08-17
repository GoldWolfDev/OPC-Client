package com.opcclient.wolf.dao;


import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class SelectDAOImpl implements SelectDAO{

    public List<OPCServer> getOPCServer() {
        List<OPCServer> list = null;
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

    public List<BaseOPC> getOPCTags(int id) {
        List<BaseOPC> list = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            list = (List<BaseOPC>)session.createCriteria(BaseOPC.class)
                    .add(Restrictions.eq("OPCServerID.id", id))
                    .list();
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
