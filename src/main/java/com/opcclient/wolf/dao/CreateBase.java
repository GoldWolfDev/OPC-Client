package com.opcclient.wolf.dao;

import com.opcclient.wolf.exeptions.OPCErrorCL;
import com.opcclient.wolf.model.BaseOPC;
import com.opcclient.wolf.model.OPCServer;
import com.opcclient.wolf.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by RafikovAR on 14.08.2016.
 */
public class CreateBase {

    public void createBase(){
        BaseOPC baseOPC = new BaseOPC();
        OPCServer server = new OPCServer();
        Session session = null;
        try{
            session = HibernateUtil.getSessionConfig().openSession();
            session.save(baseOPC);
            session.save(server);
        }catch (Exception e){
            OPCErrorCL.getInstance().errorAny(e.toString());
        }finally {
            if (session != null){
                session.close();
            }
        }
    }
}
