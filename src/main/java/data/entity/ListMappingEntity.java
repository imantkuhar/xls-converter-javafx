package data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 13.05.17.
 */
public class ListMappingEntity {

    private String tableName;
    private List<MappingEntity> mappedColumnsList = new ArrayList<MappingEntity>();

    public ListMappingEntity() {
    }

    public ListMappingEntity(String tableName, List<MappingEntity> mappedColumnsList) {
        this.tableName = tableName;
        this.mappedColumnsList = mappedColumnsList;
    }

    public void addEntity(MappingEntity mappingEntity){
        this.mappedColumnsList.add(mappingEntity);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<MappingEntity> getMappedColumnsList() {
        return mappedColumnsList;
    }

    public void setMappedColumnsList(List<MappingEntity> mappedColumnsList) {
        this.mappedColumnsList = mappedColumnsList;
    }


}
