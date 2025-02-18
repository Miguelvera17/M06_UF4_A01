package com.iticbcn.miguel_vera.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.miguel_vera.model.*;

public class ClaseDAO {

    private static SessionFactory sessionFactory;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public ClaseDAO(SessionFactory sessionFactory2) {
        sessionFactory = sessionFactory2;
    }

    public static void create(SessionFactory sesion, Clase clase) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                 session.persist(clase);
                 session.getTransaction().commit();            
            } catch (HibernateException e) {
                 if (session.getTransaction() != null) {
                      session.getTransaction().rollback();
                      System.err.println("Error en Hibernate: " + e.getMessage()); 
                  }
            } catch (Exception e) {
                 if (session.getTransaction()  != null) {
                      session.getTransaction().rollback();
                      System.err.println("Error inesperado: " + e.getMessage());
                 }
            } 
        }
    }

    public Clase find(int id) {
        Clase clase = null;
        try (Session session = sessionFactory.openSession()) {
            clase = session.find(Clase.class,id);
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } 
        return clase;
    }

    public static void update(Clase clase) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(clase);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Clase clase = session.find(Clase.class,id);
            if (clase != null) {
                session.remove(clase);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Clase> findAll() {
        List<Clase> ClaseList = null;
    	try (Session session = sessionFactory.openSession()) {
        	Query<Clase> q = session.createQuery("from Clase",Clase.class);
        	ClaseList = q.list();
        return ClaseList;
        }
    }

    public List<Object[]> findGroupBy() {
        try (Session session = sessionFactory.openSession()) {
        	Query<Object[]> q = session.createQuery(
                "SELECT horario, COUNT(*) AS Clases FROM Clase GROUP BY horario",Object[].class);
        return q.list();
        }
    }
}
