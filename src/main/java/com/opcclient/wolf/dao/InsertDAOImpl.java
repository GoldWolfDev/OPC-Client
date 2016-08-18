package com.opcclient.wolf.dao;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class InsertDAOImpl implements InsertDAO {

    public void addServerOPC(OPCServer addOPCServer) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            session.beginTransaction();
            session.save(addOPCServer);
            session.getTransaction().commit();
        }catch (Exception ex){
            OPCErrorCL.getInstance().errorSQLConnect();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    public void addItems(BaseOPC opcItem) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            session.beginTransaction();
            session.save(opcItem);
            session.getTransaction().commit();
        }catch (Exception ex){
            OPCErrorCL.getInstance().errorSQLConnect();
        }finally {
            if (session != null){
                session.close();
            }
        }

    }
}
