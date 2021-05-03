package config;

import module.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

public class DatabaseConfiguration {

    private SessionFactory sessionFactory;


    public void config() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Products.class)
                .buildSessionFactory();

    }
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
