package com.mycompany.Labholonet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("✔ [HibernateUtil] SessionFactory inicializada correctamente.");
        } catch (Throwable ex) {
            System.err.println("❌ Fallo crítico al inicializar la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("ℹ [HibernateUtil] SessionFactory cerrada de forma segura.");
        }
    }
}
