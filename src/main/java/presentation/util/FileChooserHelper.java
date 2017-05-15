package presentation.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Imant on 15.05.17.
 */
public class FileChooserHelper {

    private static final String XLS_END = "*.xls";
    private static final String XLS_TITTLE = "Choose .xls file";

    public static File getXlsFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(XLS_TITTLE, XLS_END);
        fileChooser.getExtensionFilters().add(extFilter);
        File chosenFile = fileChooser.showOpenDialog(node.getScene().getWindow());
        return chosenFile;
    }

    public static File getFileToSave(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showSaveDialog(node.getScene().getWindow());
    }
}
