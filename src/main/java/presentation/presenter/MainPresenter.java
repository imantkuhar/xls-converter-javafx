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
import presentation.view.MainView;

import java.io.File;
import java.util.List;

/**
 * Created by Imant on 15.05.17.
 */
public class MainPresenter {

    private MainView view;
    private ColumnEntityDao columnEntityDao;
    private XLSService xlsService = new XLSService();

    public void setView(MainView view) {
        this.view = view;
    }

    public void parseXlsFile(File chosenFile){
        List<MappingEntity> list = xlsService.getMappingEntities(chosenFile);
        ObservableList mappingEntitiesList = FXCollections.observableArrayList(list);
        view.fillUpTheTable(mappingEntitiesList);
    }

    public void saveToJson(ObservableList<MappingEntity> mappingEntitiesList, String tableName, String filePath) {
        ListMappingEntity listMappingEntity = new ListMappingEntity();
        listMappingEntity.setMappedColumnsList(mappingEntitiesList);
        listMappingEntity.setTableName(tableName);

        MappingJsonService mappingJsonService = new MappingJsonService();
        mappingJsonService.convertToJson(listMappingEntity, filePath);
    }

    public void saveToDB(String tableName, ObservableList<MappingEntity> mappingEntitiesList) {
        ColumnMapper columnMapper = new ColumnMapper();
        List<ColumnEntity> columnEntities = columnMapper.convertFromMappingEntitiesToColumnEntities(mappingEntitiesList);
        columnEntityDao = new ColumnEntityDao(tableName);
        columnEntityDao.createTable();
        columnEntityDao.save(columnEntities);

    }
}
