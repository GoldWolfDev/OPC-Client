package com.opcclient.wolf.dao;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
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

    public void updOPCTags(BaseOPC opc) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            session.beginTransaction();
            int i = session.createQuery("update BaseOPC set objID = :var1, eventType = :var2, objType = :var3, propType = :var4 where id = :var")
                    .setParameter("var1", opc.getObjID())
                    .setParameter("var2",opc.getEventType())
                    .setParameter("var3",opc.getObjType())
                    .setParameter("var",opc.getId())
                    .setParameter("var4",opc.getPropType()).executeUpdate();
           // session.update(opcServer);
            session.getTransaction().commit();
            System.out.println(i);
        }catch (Exception ex){
            OPCErrorCL.getInstance().errorSQLConnect();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

//    public void updOPCTags(BaseOPC opc) {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionConfig().openSession();
//            session.beginTransaction();
//            session.update(opc);
//            session.getTransaction().commit();
//        }catch (Exception ex){
//            //OPCErrorCL.getInstance().errorSQLConnect();
//            System.out.println(ex.toString());
//        }finally {
//            if (session != null){
//                session.close();
//            }
//        }
//    }
}
