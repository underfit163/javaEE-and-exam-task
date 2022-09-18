import com.tay.lab8jee.dao.*;
import com.tay.lab8jee.entities.Album;
import com.tay.lab8jee.entities.Composition;
import com.tay.lab8jee.entities.Musician;
import com.tay.lab8jee.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        AlbumDao albumDao = new AlbumDao();
        CompositionDao compDao = new CompositionDao();
        MusicianDao musicianDao = new MusicianDao();

       /* Musician Andrey = new Musician("Andrey");
        Musician Georgy = new Musician("Georgy");
        Musician Bond = new Musician("Bond");
        Musician Alex = new Musician("Alex");
        Musician Sergey = new Musician("Sergey");

        Album Loaded = new Album("Laded","Pop");
        Album Paranoid = new Album("Paranoid","Rock");
        Album Tim = new Album("Tim","Pop");
        Album Closer = new Album("Closer","Rap");
        Album Desire = new Album("Desire","Jazz");

        Composition Squad = new Composition("Squad",50);
        Composition Cream = new Composition("Cream",7);
        Composition Soda = new Composition("Soda",4);
        Composition Gorit = new Composition("Gorit",8);
        Composition Kiss = new Composition("Kiss",20);
        Composition Girl = new Composition("Girl",20);
        Composition Boy = new Composition("Boy",3);

        //-----------------------------------Заполнение данными:---------------------------------------------------
        Andrey.addAlbum(Loaded);
        Georgy.addAlbum(Paranoid);
        Alex.addAlbum(Tim);
        Bond.addAlbum(Closer);
        Bond.addAlbum(Desire);

        Paranoid.addComposition(Squad);
        Paranoid.addComposition(Cream);
        Paranoid.addComposition(Soda);
        Closer.addComposition(Kiss);
        Desire.addComposition(Girl);
        Desire.addComposition(Boy);
        Tim.addComposition(Gorit);

        musicianDao.save(Andrey);
        musicianDao.save(Georgy);
        musicianDao.save(Bond);
        musicianDao.save(Alex);
        musicianDao.save(Sergey);*/

        //-----------------------Вывод данных:---------------
        /*for (Musician item :
                musicianDao.selectAll()) {
            System.out.println(item);
        }
        for (Album item :
                albumDao.selectAll()) {
            System.out.println(item);
            //System.out.println(albumDao.findByID(item.getIdAlbum()) + " НАПИСАН: " + musicianDao.findByID(item.getMusician().getIdMusician()));
        }
        for (Composition item :
                compDao.selectAll()) {
            System.out.println(item);
            //System.out.println(compDao.findByID(item.getIdComposition()) + " НАХОДИТСЯ: " + albumDao.findByID(item.getAlbum().getIdAlbum()));
        }*/
        //--------------------удаление и редактирование данных в любой из таблиц-----------
        /*compDao.deleteByID(7);
        for (Composition item :
                compDao.selectAll()) {
            System.out.println(item);
        }

        Composition Gorit = new Composition("Gorit", 8);
        compDao.insertCompInAlbum(5, Gorit);
        for (Composition item :
                compDao.selectAll()) {
            System.out.println(item);
        }*/

        //-------------update через язык запросов---------
       /* try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Composition set durationComposition = 100 where nameComposition = :name");
            query.setParameter("name", "Soda");
            query.executeUpdate();
            transaction.commit();
        }
        for (Composition item :
                compDao.selectAll()) {
            System.out.println(item);
        }*/
        //-------------update через метод-----------------
       /* for (Composition item :
                compDao.selectAll()) {
            if (item.getNameComposition().equals("Soda")) {
                item.setDurationComposition(10);
                compDao.update(item);
                break;
            }
        }
        for (Composition item :
                compDao.selectAll()) {
            System.out.println(item);
        }*/
    }
}
