package data.dao;

import data.entity.ColumnEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Imant on 14.05.17.
 */
public class ColumnEntityDao {

    private EntityManagerFactory managerFactory = EntityFactory.getEntityManagerFactory();
    private EntityManager entityManager = managerFactory.createEntityManager();

    public void save(ColumnEntity columnEntity) {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(columnEntity);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void save(List<ColumnEntity> columnEntities) {
        for (ColumnEntity columnEntity : columnEntities) {
            save(columnEntity);
        }
    }
}
