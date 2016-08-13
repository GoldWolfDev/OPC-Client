package com.opcclient.wolf.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static SessionFactory sessionConfig = null;

    private HibernateUtil(){}

    static {

        try {
            if(sessionConfig == null){
                sessionConfig = new Configuration().configure("hibernate-config.cfg.xml").buildSessionFactory();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static SessionFactory getSessionConfig() {

        return sessionConfig;
    }
}
