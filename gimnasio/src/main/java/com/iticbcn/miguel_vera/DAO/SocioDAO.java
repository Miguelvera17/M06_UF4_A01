package com.iticbcn.miguel_vera.DAO;

import org.hibernate.SessionFactory;
import com.iticbcn.miguel_vera.model.*;

public class SocioDAO extends GenDAOImpl<Socio> {

    public SocioDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Socio.class);
    }
}