package presentation.presenter;

import data.dao.ColumnEntityDao;
import data.entity.ColumnEntity;
import data.entity.ListMappingEntity;
import data.entity.MappingEntity;
import domain.mapper.ColumnMapper;
import domain.service.MappingJsonService;
import domain.service.XLSService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

/**
 * Created by Imant on 15.05.17.
 */
public class MainPresenter {

    public MainPresenter() {
    }

    File chosenFile;
    ColumnEntityDao columnEntityDao;

    public File getChosenFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);
        chosenFile = fileChooser.showOpenDialog(node.getScene().getWindow());
        return chosenFile;
    }

    public File getChosenFileToSaveJson(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = new FileChooser();
        chosenFile = fileChooser.showSaveDialog(node.getScene().getWindow());
        return chosenFile;
    }

    public void saveEntitiesListToJson(ObservableList<MappingEntity> mappingEntitiesList, TextField tfTableName, String filePath) {
        ListMappingEntity listMappingEntity = new ListMappingEntity();
        listMappingEntity.setMappedColumnsList(mappingEntitiesList);
        listMappingEntity.setTableName(tfTableName.getText());

        MappingJsonService mappingJsonService = new MappingJsonService();
        mappingJsonService.convertToJson(listMappingEntity, filePath);
    }

    public void saveEntitiesListToDB(TextField tfTableName, ColumnMapper columnMapper, ObservableList<MappingEntity> mappingEntitiesList) {
        columnEntityDao = new ColumnEntityDao(tfTableName.getText());
        columnEntityDao.createTable();
        List<ColumnEntity> columnEntities = columnMapper.convertFromMappingEntitiesToColumnEntities(mappingEntitiesList);
        columnEntityDao.saveColumnEntitiesListToDB(columnEntities);
    }

    public void fillUpTheTable(ObservableList<MappingEntity> mappingEntitiesList, TableColumn tcFileColumnName, TableColumn tcBDColumnName, TableView<MappingEntity> tvColumnsTable) {
        tcFileColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("fileColumnName"));
        tcBDColumnName.setCellValueFactory(new PropertyValueFactory<MappingEntity, String>("dbColumnName"));
        tcBDColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcBDColumnName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MappingEntity, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MappingEntity, String> t) {
                        ((MappingEntity) t.getTableView().getItems().get(t.getTablePosition().getRow())
                        ).setDbColumnName(t.getNewValue());
                    }
                }
        );
        tvColumnsTable.setEditable(true);
        tvColumnsTable.setItems(mappingEntitiesList);
    }
}
