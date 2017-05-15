import domain.utils.PropertiesHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Imant on 11.05.17.
 */
public class StartFxApp extends Application {

    private Stage mainStage;
    private Scene mainScene;

    private static StartFxApp instance;

    public static StartFxApp getInstance() {
        return instance;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;

        String mainView = PropertiesHolder.getProperty("MAIN_VIEW_ROOT");
        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource(mainView));

        mainScene = new Scene(mainParent);
        mainStage = stage;
        mainStage.setTitle("Parsing program");
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
