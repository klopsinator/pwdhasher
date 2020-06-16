package pwdhasher;

import com.google.common.eventbus.EventBus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author Andreas Werner
 */
@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext springContext;

    private Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("pwdhasher");
        stage.setScene(new Scene(rootNode));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:icon.png"));

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FXMLLoader fxmlLoader(ConfigurableApplicationContext springContext) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
        return fxmlLoader;
    }

    /**
     * @return
     */
    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}