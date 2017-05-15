package presentation.presenter;

import data.dao.ColumnEntityDao;
import data.entity.ColumnEntity;
import data.entity.ListMappingEntity;
import data.entity.MappingEntity;
import domain.mapper.ColumnMapper;
import domain.service.MappingJsonService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
}
