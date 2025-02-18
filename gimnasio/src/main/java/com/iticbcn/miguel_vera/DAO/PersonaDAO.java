package com.iticbcn.miguel_vera.DAO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.iticbcn.miguel_vera.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PersonaDAO {
    
    private static SessionFactory sessionFactory;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public PersonaDAO(SessionFactory sessionFactory2) {
        sessionFactory = sessionFactory2;
    }

    public static void create(SessionFactory sesion, Persona persona) {
        try (Session session = sesion.openSession()) {
            session.beginTransaction();
            try {
                 session.persist(persona);
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

    public Persona find(int id) {
        Persona persona = null;
        try (Session session = sessionFactory.openSession()) {
            persona = session.find(Persona.class,id);
        } catch (HibernateException e) {
            System.err.println("Error en Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } 
        return persona;
    }

    public static void update(Persona persona) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(persona);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Persona persona = session.find(Persona.class,id);
            if (persona != null) {
                session.remove(persona);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Persona> findAll() {
        List<Persona> PersonaList = null;
    	try (Session session = sessionFactory.openSession()) {
        	Query<Persona> q = session.createQuery("from Persona",Persona.class);
        	PersonaList = q.list();
        return PersonaList;
        }
    }

    public List<Object[]> findGroupBy() {
        try (Session session = sessionFactory.openSession()) {
        	Query<Object[]> q = session.createQuery(
                "SELECT nombre, COUNT(*) FROM Persona GROUP BY nombre",Object[].class);
        return q.list();
        }
    }
}
