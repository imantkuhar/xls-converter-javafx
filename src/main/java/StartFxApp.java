import domain.utils.HibernateUtil;
import domain.utils.PropertiesHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

/**
 * Created by Imant on 11.05.17.
 */
public class StartFxApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String mainView = PropertiesHolder.getProperty("MAIN_VIEW_ROOT");
        Parent mainParent = FXMLLoader.load(getClass().getClassLoader().getResource(mainView));
        Scene mainScene = new Scene(mainParent);
        Stage mainStage = stage;
        mainStage.setTitle("XlsConverter JavaFX");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtil.shutdown();
    }
}
