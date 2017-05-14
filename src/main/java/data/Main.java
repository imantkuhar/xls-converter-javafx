package data;

import data.dao.ColumnEntityDao;

/**
 * Created by Imant on 14.05.17.
 */
public class Main {
    public static void main(String[] args) {
        ColumnEntityDao columnEntityDao = new ColumnEntityDao();
        columnEntityDao.saveToDB("NEWTABLE", null);
    }
}
