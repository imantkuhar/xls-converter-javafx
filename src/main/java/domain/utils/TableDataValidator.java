package domain.utils;

import data.entity.MappingEntity;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Imant on 15.05.17.
 */
public class TableDataValidator extends BaseValidator {

    public static boolean validateSaving(String filePath, String tableName, ObservableList<MappingEntity> mappingEntitiesList) {
        if (filePath.isEmpty() || mappingEntitiesList.isEmpty() || !tableName.matches(COLUMN_NAME_PATTERN)) {
            return false;
        }
        return true;
    }
}
