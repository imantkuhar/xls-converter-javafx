package data.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Imant on 14.05.17.
 */
public class EntityFactory {
    private static EntityManagerFactory instance;

    private EntityFactory() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (instance == null)
            instance = Persistence.createEntityManagerFactory("hibernate-unit");
        return instance;
    }
}
