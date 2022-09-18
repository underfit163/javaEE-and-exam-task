package com.tay.lab8jee.dao;


import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import com.tay.lab8jee.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MusicianDao extends AbstractDao<Musician> {

    @Override
    public Musician findByID(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Musician.class, id);
        }
    }

    @Override
    public void deleteByID(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Musician.class, id));
            transaction.commit();
        }
    }

    @Override
    public List<Musician> selectAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("From Musician order by idMusician", Musician.class).list();
        }
    }

    public List<Composition> selectComposition(int id) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "from Composition c where c.album.musician.idMusician = :pr1";
            Query<Composition> query = session.createQuery(hql, Composition.class);
            query.setParameter("pr1", id);
            return query.list();
        }
    }
}
