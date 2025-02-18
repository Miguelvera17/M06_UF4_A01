package com.iticbcn.miguel_vera.DAO;

import org.hibernate.SessionFactory;
import com.iticbcn.miguel_vera.model.*;

public class PersonaDAO extends GenDAOImpl<Persona> {

    public PersonaDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Persona.class);
    }
}
