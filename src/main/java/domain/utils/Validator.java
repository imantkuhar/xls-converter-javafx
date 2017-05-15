package domain.utils;

import data.entity.MappingEntity;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Created by Imant on 15.05.17.
 */
public class Validator extends BaseValidator {

    public static boolean validateFilePath(TextField tfFilePath, Button button, ContextMenu passValidator) {
        if (tfFilePath.getText().isEmpty()) {
            passValidator.getItems().clear();
            passValidator.getItems().add(new MenuItem("Please choose a file"));
            passValidator.show(button, Side.TOP, 0, 0);
            return false;
        }
        return true;
    }

    public static boolean validateTable(ObservableList<MappingEntity> mappingEntitiesList, Button button, ContextMenu passValidator) {
        if (mappingEntitiesList.size() == 0) {
            passValidator.getItems().clear();
            passValidator.getItems().add(new MenuItem("The table is empty"));
            passValidator.show(button, Side.TOP, 0, 0);
            return false;
        }
        return true;
    }

    public static boolean validateTableName(TextField tfTableName, Button button, ContextMenu passValidator) {
        if (tfTableName.getText().isEmpty() || !tfTableName.getText().matches(COLUMN_NAME_PATTERN)) {
            passValidator.getItems().clear();
            passValidator.getItems().add(new MenuItem("Please enter table name"));
            passValidator.show(button, Side.TOP, 0, 0);
            return false;
        }
        return true;
    }
}
