package prototype;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXMLLoader;

public class ControllerFactory {
    public Controller createController(String url) {
        return createController(url, null);
    }

    private Controller createController(String url, Controller parent) {
        Controller controller = loadController(url);
        controller.setParent(parent);    

        List<String> children = controller.getChildren();
        if (children.isEmpty()) return controller;

        for (String child : children) {
            Controller childController = createController(getUrl(child), controller);
            controller.addChild(childController);
        }
        return controller;
    }

    private String getUrl(String fieldName) {
        return fieldName + ".fxml";
    }

    // ToDo:Change FXMLLoder to singleton
    private Controller loadController(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            if (loader.getController() == null)
                System.out.println("controlle is null");
            return (Controller)loader.getController();
        } catch (IOException e) {
            // ToDo:tread exception (wrap with custom exception)
            e.printStackTrace();
            return null;
        }
    }
}
