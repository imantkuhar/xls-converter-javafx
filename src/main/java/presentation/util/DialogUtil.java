package presentation.util;

import javafx.scene.control.Alert;

/**
 * Created by Imant on 15.05.17.
 */
public class DialogUtil {

    public static void showInfoDialog(DialogMessage message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message.getName());
        alert.showAndWait();
    }
}
