package domain.utils;

import data.entity.MappingEntity;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Imant on 15.05.17.
 */
public class TableDataValidator extends BaseValidator {

    public static boolean validateSavingInDB(String filePath, String tableName, ObservableList<MappingEntity> mappingEntitiesList) {
        if (filePath.isEmpty() || !tableName.matches(COLUMN_NAME_PATTERN) || mappingEntitiesList.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validateSavingInJson(String filePath, ObservableList<MappingEntity> mappingEntitiesList) {
        if (filePath.isEmpty() || mappingEntitiesList.isEmpty()) {
            return false;
        }
        return true;
    }
}
