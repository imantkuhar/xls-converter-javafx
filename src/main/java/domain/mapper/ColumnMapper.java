package domain.mapper;

import data.entity.ColumnEntity;
import data.entity.MappingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imant on 13.05.17.
 */
public class ColumnMapper {

    public List<ColumnEntity> convertToColumnEntities(List<String> columnNames) {
        List<ColumnEntity> columnEntities = new ArrayList<ColumnEntity>();
        for (String columnName : columnNames) {
            columnEntities.add(new ColumnEntity(columnName));
        }
        return columnEntities;
    }

    public List<MappingEntity> convertToMappingEntities(List<String> columnNames) {
        List<MappingEntity> mappingEntitiesList = new ArrayList<MappingEntity>();
        for (String columnName : columnNames) {
            MappingEntity mappingEntity = new MappingEntity();
            mappingEntity.setFileColumnName(columnName);
            mappingEntity.setDbColumnName(columnName);
            mappingEntitiesList.add(mappingEntity);
        }
        return mappingEntitiesList;
    }

    public List<ColumnEntity> convertFromMappingEntitiesToColumnEntities(List<MappingEntity> mappingEntities) {
        List<ColumnEntity> columnEntities = new ArrayList<ColumnEntity>();
        for (MappingEntity entity : mappingEntities) {
            ColumnEntity columnEntity = new ColumnEntity(entity.getFileColumnName());
            columnEntities.add(columnEntity);
        }
        return columnEntities;
    }

    public List<MappingEntity> convertFromColumnEntitiesToMappingEntities(List<ColumnEntity> columnEntities) {
        List<MappingEntity> mappingEntities = new ArrayList<MappingEntity>();

        for (ColumnEntity entity : columnEntities) {
            MappingEntity mappingEntity = new MappingEntity();
            mappingEntity.setFileColumnName(entity.getDbColumnName());
            mappingEntity.setDbColumnName(entity.getDbColumnName());
            mappingEntities.add(mappingEntity);
        }

        return mappingEntities;
    }
}
