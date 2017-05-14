package presentation.controllers;

import data.dao.ColumnEntityDao;
import data.entity.ColumnEntity;
import data.entity.ListMappingEntity;
import data.entity.MappingEntity;
import domain.mapper.ColumnMapper;
import domain.service.MappingJsonService;
import domain.service.XLSService;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Imant on 14.05.17.
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
    private ColumnMapper columnMapper = new ColumnMapper();
    private XLSService xlsService = new XLSService();
    private ColumnEntityDao columnEntityDao;

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
                fillUpTheTable();
            }
        });
    }

    private void setButtonSaveToDBListener() {
        btSaveToDB.setOnAction(event -> {
                    if (!(mappingEntitiesList == null) && !tfTableName.getText().isEmpty()) {
                        columnEntityDao = new ColumnEntityDao(tfTableName.getText());
                        columnEntityDao.createTable();
                        List<ColumnEntity> columnEntities = columnMapper.convertFromMappingEntitiesToColumnEntities(mappingEntitiesList);
                        columnEntityDao.saveColumnEntitiesListToDB(columnEntities);
                    }
                }
        );
    }

    private void setButtonSaveToJsonListener() {
        btSaveToJson.setOnAction(event -> {
            if (!(mappingEntitiesList == null)) {
                Node node = (Node) event.getSource();
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(node.getScene().getWindow());
                if (file != null) {
                    saveEntitiesListToJson(file.getPath());
                }
            }
        });
    }

    private void saveEntitiesListToJson(String filePath) {
        ListMappingEntity listMappingEntity = new ListMappingEntity();
        listMappingEntity.setMappedColumnsList(mappingEntitiesList);
        listMappingEntity.setTableName(tfTableName.getText());

        MappingJsonService mappingJsonService = new MappingJsonService();
        mappingJsonService.convertToJson(listMappingEntity, filePath);
    }

    private void fillUpTheTable() {
        List<MappingEntity> list = xlsService.getMappingEntities(chosenFile);
        mappingEntitiesList = FXCollections.observableArrayList(list);
        tcFileColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("fileColumnName"));
        tcBDColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("dbColumnName"));
        tcBDColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcBDColumnName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MappingEntity, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MappingEntity, String> t) {
                        ((MappingEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setFileColumnName(t.getNewValue());
                    }
                }
        );
        tvColumnsTable.setEditable(true);
        tvColumnsTable.setItems(mappingEntitiesList);
    }
}

