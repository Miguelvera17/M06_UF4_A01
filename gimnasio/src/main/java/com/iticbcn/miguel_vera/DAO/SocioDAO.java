package com.iticbcn.miguel_vera.DAO;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.iticbcn.miguel_vera.model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class SocioDAO {

    private static SessionFactory sessionFactory;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public SocioDAO(SessionFactory sessionFactory2) {
        sessionFactory = sessionFactory2;
    }


    public static void create(SessionFactory sesion, Socio socio) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                 session.persist(socio);
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

    public Socio find(int id) {
        Socio socio = null;
        try (Session session = sessionFactory.openSession()) {
            socio = session.find(Socio.class,id);
            if (socio != null) {
                Hibernate.initialize(socio.getPersona());
                Hibernate.initialize(socio.getGimnasio());
            }
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } 
        return socio;
    }

    public static void update(Socio socio) {
         Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(socio);
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
            Socio socio = session.find(Socio.class,id);
            if (socio != null) {
                session.remove(socio);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Socio> findAll() {
        List<Socio> SocioList = null;
    	try (Session session = sessionFactory.openSession()) {
        	Query<Socio> q = session.createQuery("from Socio",Socio.class);
        	SocioList = q.list();
        return SocioList;
        }
    }

    public List<Object[]> findGroupBy() {
        try (Session session = sessionFactory.openSession()) {
        	Query<Object[]> q = session.createQuery(
                "SELECT fechaInscripcion, COUNT(*) AS cantidad_socios FROM Socio GROUP BY fechaInscripcion",Object[].class);
        return q.list();
        }
    }
}
