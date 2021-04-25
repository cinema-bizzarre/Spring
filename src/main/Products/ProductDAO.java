import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
        private SessionFactory sessionFactory;
        private Session session;

    public  void config (){
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Products.class)
                .buildSessionFactory();

        Session session = null;

    }
    public void createProducts (){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Products products = new Products("sundress", 4500);
        System.out.println(products);
        session.save(products);
        System.out.println(products);
        session.getTransaction().commit();
    }

    public void readProducts(){
       session = sessionFactory.getCurrentSession();
       session.beginTransaction();
       Products productsFromDb = session.get(Products.class, 1L);
       System.out.println(productsFromDb);
       session.getTransaction().commit();
    }

    public void showProducts(){
        session = sessionFactory.getCurrentSession();
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
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Products productsFromDb = session.get(Products.class, 1L);
        System.out.println(productsFromDb);
        productsFromDb.setPrice(4800);
        productsFromDb.setTitle("overall");
        session.getTransaction().commit();
    }
    public void deleteProducts(){
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Products productsFromDb = session.get(Products.class, 1L);
        session.remove(productsFromDb);
        session.getTransaction().commit();
    }

}
