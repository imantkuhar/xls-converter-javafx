package presentation.view;

import presentation.util.DialogMessage;
import data.entity.MappingEntity;
import domain.utils.TableDataValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import presentation.util.DialogUtil;
import presentation.util.FileChooserHelper;
import presentation.presenter.MainPresenter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Imant on 14.05.17.
 */
public class MainView implements Initializable {

    @FXML
    private Button btChooseFile, btSaveToDB, btSaveToJson;
    @FXML
    private TextField tfFilePath, tfTableName;
    @FXML
    private TableView<MappingEntity> tvColumnsTable;
    @FXML
    private TableColumn tcFileColumnName, tcBDColumnName;
    private ObservableList<MappingEntity> mappingEntitiesList = FXCollections.observableArrayList();
    private MainPresenter presenter = new MainPresenter();


    public void initialize(URL location, ResourceBundle resources) {
        setButtonChooseFileListener();
        setButtonSaveToDBListener();
        setButtonSaveToJsonListener();
        presenter.setView(this);
    }

    private void setButtonChooseFileListener() {
        btChooseFile.setOnAction(event -> {
            File chosenFile = FileChooserHelper.getXlsFile(event);
            if (chosenFile != null) {
                tfFilePath.setText(chosenFile.getAbsolutePath());
                presenter.parseXlsFile(chosenFile);
            }
        });
    }

    private void setButtonSaveToJsonListener() {
        btSaveToJson.setOnAction(event -> {
            String filePath = tfFilePath.getText();
            String tableName = tfTableName.getText();
            if (TableDataValidator.validateSaving(filePath, tableName, mappingEntitiesList)) {
                File chosenFile = FileChooserHelper.getFileToSave(event);
                if (chosenFile != null) {
                    presenter.saveToJson(mappingEntitiesList, tfTableName.getText(), chosenFile.getPath());
                    showInfoDialog(DialogMessage.SUCCESS_JSON_SAVE);
                }
            } else {
                showInfoDialog(DialogMessage.ERROR_INPUT);
            }
        });
    }

    private void setButtonSaveToDBListener() {
        btSaveToDB.setOnAction(event -> {
                    String filePath = tfFilePath.getText();
                    String tableName = tfTableName.getText();
                    if (TableDataValidator.validateSaving(filePath, tableName, mappingEntitiesList)) {
                        presenter.saveToDB(tableName, mappingEntitiesList);
                        tfTableName.clear();
                        showInfoDialog(DialogMessage.SUCCESS_DB_SAVE);
                    } else {
                        showInfoDialog(DialogMessage.ERROR_INPUT);
                    }
                }
        );
    }

    public void fillUpTheTable(ObservableList<MappingEntity> mappingEntitiesList) {
        this.mappingEntitiesList = mappingEntitiesList;
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

    private void showInfoDialog(DialogMessage dialogMessage) {
        DialogUtil.showInfoDialog(dialogMessage);
    }
}

