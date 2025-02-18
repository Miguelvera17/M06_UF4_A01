package com.iticbcn.miguel_vera.DAO;

import org.hibernate.SessionFactory;
import com.iticbcn.miguel_vera.model.*;

public class GimnasioDAO extends GenDAOImpl<Gimnasio> {

    public GimnasioDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Gimnasio.class);
    }
}
