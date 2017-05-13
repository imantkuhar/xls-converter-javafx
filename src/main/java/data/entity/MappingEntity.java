package data.entity;

/**
 * Created by Imant on 13.05.17.
 */
public class MappingEntity {

    private String fileColumnName;
    private String dbColumnName;

    public MappingEntity() {
    }

    public MappingEntity(String fileColumnName, String dbColumnName) {
        this.fileColumnName = fileColumnName;
        this.dbColumnName = dbColumnName;
    }

    public String getFileColumnName() {
        return fileColumnName;
    }

    public void setFileColumnName(String fileColumnName) {
        this.fileColumnName = fileColumnName;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }
}
