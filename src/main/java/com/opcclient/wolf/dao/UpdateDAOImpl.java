package com.opcclient.wolf.dao;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by RafikovAR on 10.08.2016.
 */
public class UpdateDAOImpl implements UpdateDAO {

    public void updServerOPC(OPCServer opcServer) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            session.beginTransaction();
            session.update(opcServer);
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
