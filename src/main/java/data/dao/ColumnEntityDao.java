package data.dao;

import data.entity.ColumnEntity;
import domain.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Imant on 14.05.17.
 */
public class ColumnEntityDao {

//    private EntityManagerFactory managerFactory = EntityFactory.getEntityManagerFactory();
//    private EntityManager entityManager = managerFactory.createEntityManager();

    public void save(ColumnEntity columnEntity) {
//        EntityManager entityManager = managerFactory.createEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.merge(columnEntity);
//            entityManager.getTransaction().commit();
//
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//        } finally {
//            entityManager.close();
//        }
    }

    public void save(List<ColumnEntity> columnEntities) {
        for (ColumnEntity columnEntity : columnEntities) {
            save(columnEntity);
        }
    }

    public void saveToDB(String tableName, List<ColumnEntity> columnEntities) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        String name = "NEWTABLE";

        Transaction transaction = session.beginTransaction();

//        String sqlQuery = "CREATE TABLE " + tableName.toUpperCase() + "(ID INT PRIMARY KEY, NAME VARCHAR(255))";
        String sqlQuery = "CREATE TABLE PERSON (id int primary key, name varchar(255))";

        String sqlQuery1 = "CREATE TABLE " + name;
        Query query = session.createQuery(sqlQuery);
        query.executeUpdate();
        transaction.commit();
    }
}
