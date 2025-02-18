package com.iticbcn.miguel_vera.DAO;

import org.hibernate.SessionFactory;
import com.iticbcn.miguel_vera.model.*;

public class ClaseDAO extends GenDAOImpl<Clase> {

    public ClaseDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Clase.class);
    }
}