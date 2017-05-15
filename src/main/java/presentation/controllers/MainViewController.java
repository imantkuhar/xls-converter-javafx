package presentation.controllers;

import data.dao.ColumnEntityDao;
import data.entity.MappingEntity;
import domain.mapper.ColumnMapper;
import domain.service.XLSService;
import domain.utils.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import presentation.presenter.MainPresenter;

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
    private ObservableList<MappingEntity> mappingEntitiesList = FXCollections.observableArrayList();
    private ColumnMapper columnMapper = new ColumnMapper();
    private ContextMenu passValidator = new ContextMenu();
    private XLSService xlsService = new XLSService();
    private MainPresenter presenter;

    File chosenFile;

    public void initialize(URL location, ResourceBundle resources) {
        setButtonChooseFileListener();
        setButtonSaveToDBListener();
        setButtonSaveToJsonListener();
        presenter = new MainPresenter();
    }

    private void setButtonChooseFileListener() {
        btChooseFile.setOnAction(event -> {
            chosenFile = presenter.getChosenFile(event);
            if (chosenFile != null) {
                tfFilePath.setText(chosenFile.getAbsolutePath());
                List<MappingEntity> list = xlsService.getMappingEntities(chosenFile);
                mappingEntitiesList = FXCollections.observableArrayList(list);
                presenter.fillUpTheTable(mappingEntitiesList, tcFileColumnName, tcBDColumnName, tvColumnsTable);
            }
        });
    }

    private void setButtonSaveToJsonListener() {
        btSaveToJson.setOnAction(event -> {
            if (Validator.validateFilePath(tfFilePath, btSaveToJson, passValidator) &&
                    Validator.validateTable(mappingEntitiesList, btSaveToJson, passValidator)) {
                chosenFile = presenter.getChosenFileToSaveJson(event);
                if (chosenFile != null) {
                    presenter.saveEntitiesListToJson(mappingEntitiesList, tfTableName, chosenFile.getPath());
                }
            }
        });
    }

    private void setButtonSaveToDBListener() {
        btSaveToDB.setOnAction(event -> {
                    if (Validator.validateFilePath(tfFilePath, btSaveToJson, passValidator)
                            && Validator.validateTable(mappingEntitiesList, btSaveToJson, passValidator) &&
                            Validator.validateTableName(tfTableName, btSaveToDB, passValidator)) {
                        presenter.saveEntitiesListToDB(tfTableName, columnMapper, mappingEntitiesList);
                        tfTableName.clear();
                    }
                }
        );
    }
}

