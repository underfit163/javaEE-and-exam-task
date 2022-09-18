package com.tay.lab8jee.dao;

import com.tay.lab8jee.entities.Album;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompositionDao extends AbstractDao<Composition> {

    @Override
    public Composition findByID(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Composition.class, id);
        }
    }

    @Override
    public void deleteByID(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Composition.class, id));
            transaction.commit();
        }
    }

    @Override
    public List<Composition> selectAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("From Composition order by idComposition", Composition.class).list();
        }
    }

    public void insertCompInAlbum(int albumId, Composition composition) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Album album = session.get(Album.class, albumId);
            album.addComposition(composition);

           // session.update(album);
            session.save(composition);
            transaction.commit();
        }
    }


}

