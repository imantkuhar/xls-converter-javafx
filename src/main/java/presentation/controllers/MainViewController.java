package presentation.controllers;

import data.entity.MappingEntity;
import domain.service.XLSService;
import domain.utils.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Imant on 17.11.16.
 */
public class MainViewController implements Initializable {

    @FXML
    private Pane chooseFilePane, tablePane;
    @FXML
    private Button btChooseFile, btSaveToDB, btSaveToJson;
    @FXML
    private TextField tfFilePath, tfTableName;
    @FXML
    private TableView<MappingEntity> tvColumnsTable;
    @FXML
    private TableColumn tcFileColumnName, tcBDColumnName;
    private ObservableList<MappingEntity> mappingEntitiesList;
    private XLSService xlsService = new XLSService();

    File chosenFile;

    public void initialize(URL location, ResourceBundle resources) {
        setButtonChooseFileListener();
        setButtonSaveToDBListener();
        setButtonSaveToJsonListener();
    }

    private void setButtonChooseFileListener() {
        btChooseFile.setOnAction(event -> {

            Node node = (Node) event.getSource();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
            fileChooser.getExtensionFilters().add(extFilter);
            chosenFile = fileChooser.showOpenDialog(node.getScene().getWindow());
            if (chosenFile != null) {
                tfFilePath.textProperty().set(chosenFile.getAbsolutePath());

                List<MappingEntity> list = xlsService.getMappingEntities(chosenFile);
                mappingEntitiesList = FXCollections.observableArrayList(list);
                tcFileColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("fileColumnName"));
                tcBDColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("dbColumnName"));
                tcBDColumnName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent event) {
                        System.out.println();
                    }
                });
                tvColumnsTable.setItems(mappingEntitiesList);
            }
        });
    }

    private void setButtonSaveToDBListener() {
        btSaveToDB.setOnAction(event -> {
        });
    }

    private void setButtonSaveToJsonListener() {
        btSaveToJson.setOnAction(event -> {
        });
    }
}
