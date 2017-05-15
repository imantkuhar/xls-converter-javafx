package data.dao;

import data.entity.ColumnEntity;
import domain.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Imant on 14.05.17.
 */
public class ColumnEntityDao {

    private String tableName;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public ColumnEntityDao(String tableName) {
        this.tableName = tableName;
    }

    public void createTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY, column_name VARCHAR(75))";
        Query query = session.createNativeQuery(sqlQuery);
        query.executeUpdate();
        transaction.commit();
    }

    public void save(List<ColumnEntity> columnEntities) {
        for (ColumnEntity columnEntity : columnEntities) {
            save(columnEntity);
        }
    }

    public void save(ColumnEntity columnEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String sqlPrepQuery = "INSERT INTO " + tableName + " (COLUMN_NAME) VALUES (?1)";
        Query query = session.createNativeQuery(sqlPrepQuery);
        query.setParameter(1, columnEntity.getDbColumnName());
        query.executeUpdate();
        transaction.commit();
    }
}