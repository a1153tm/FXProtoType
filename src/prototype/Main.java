package prototype;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        ControllerFactory factory = new ControllerFactory();
        Controller rootController = factory.createController("Login.fxml");
        
        Group rootView = new Group();
        rootView.getChildren().add(rootController.getView());
        stage.setScene(new Scene(rootView));
        stage.show();
    }
    
}
