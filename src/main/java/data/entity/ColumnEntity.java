package data.entity;

/**
 * Created by Imant on 13.05.17.
 */
public class ColumnEntity {

    private int id;

    private String dbColumnName;

    public ColumnEntity() {
    }

    public ColumnEntity(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }
}
