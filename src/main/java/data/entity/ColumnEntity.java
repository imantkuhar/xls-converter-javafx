package data.entity;

/**
 * Created by Imant on 13.05.17.
 */
public class ColumnEntity {

    private int id;
    private String columnName;

    public ColumnEntity() {
    }

    public ColumnEntity(int id, String columnName) {
        this.id = id;
        this.columnName = columnName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
