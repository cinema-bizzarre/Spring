package repository;

import config.DatabaseConfiguration;
import module.Products;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductDAO {

    private DatabaseConfiguration databaseConfiguration;
    private Session session;

    @Autowired
    public void productsRepository (DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public void createProducts (){
        session = databaseConfiguration.getCurrentSession();
        session.beginTransaction();
        Products products = new Products("sundress", 4500);
        System.out.println(products);
        session.save(products);
        System.out.println(products);
        session.getTransaction().commit();
    }

    public void readProducts(){
       session = databaseConfiguration.getCurrentSession();
       session.beginTransaction();
       Products productsFromDb = session.get(Products.class, 1L);
       System.out.println(productsFromDb);
       session.getTransaction().commit();
    }

    public void showProducts(){
        session = databaseConfiguration.getCurrentSession();
        session.beginTransaction();
        Products productsFromDb = session.createQuery("SELECT i FROM Products i WHERE i.id = :id", Products.class)
                .setParameter("id", 4L)
                .getSingleResult();
        System.out.println(productsFromDb);
        List<Products> products = session.createQuery("SELECT i FROM Products i", Products.class).getResultList();
        System.out.println(products);
        session.getTransaction().commit();
    }

    public void updateProducts(){
        session = databaseConfiguration.getCurrentSession();
        session.beginTransaction();
        Products productsFromDb = session.get(Products.class, 1L);
        System.out.println(productsFromDb);
        productsFromDb.setPrice(4800);
        productsFromDb.setTitle("overall");
        session.getTransaction().commit();
    }
    public void deleteProducts(){
        session = databaseConfiguration.getCurrentSession();
        session.beginTransaction();
        Products productsFromDb = session.get(Products.class, 1L);
        session.remove(productsFromDb);
        session.getTransaction().commit();
    }

}
