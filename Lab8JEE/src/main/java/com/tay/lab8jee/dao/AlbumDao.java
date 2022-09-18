package com.tay.lab8jee.dao;

import com.tay.lab8jee.entities.Album;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import com.tay.lab8jee.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AlbumDao extends AbstractDao<Album> {

    @Override
    public Album findByID(int id) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Album.class,id);
        }
    }

    @Override
    public void deleteByID(int id) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Album.class,id));
            transaction.commit();
        }
    }

    @Override
    public List<Album> selectAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("From Album order by idAlbum", Album.class).list();
        }
    }

    public void insertAlbumInMusician(int musicianId, Album album) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Musician musician = session.get(Musician.class, musicianId);
            musician.addAlbum(album);

            //session.update(musician);
            session.save(album);
            transaction.commit();
        }
    }

    public List<Composition> selectComposition(int id, double duration) {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "from Composition c where c.album.idAlbum = :pr1 and c.durationComposition >= :pr2";
            Query<Composition> query = session.createQuery(hql, Composition.class);
            query.setParameter("pr1", id);
            query.setParameter("pr2", duration);
            return query.list();
        }
    }
}
