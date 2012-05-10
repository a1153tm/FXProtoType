package prototype;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Controller implements Initializable {
    protected Controller parent;

    @FXML
    protected Pane view;

    @Override
    abstract public void initialize(URL url, ResourceBundle rb);

    public final Pane getView() {
        return view;
    }

    public final void setParent(Controller parent) {
        this.parent = parent;
    }

    public List<String> getChildren() {
        List<String> children = new ArrayList<String>();
        for (Field f : getControllerFields())
            children.add(f.getName().replaceAll("Controller$", ""));
        return children;
    }

    public final void addChild(Controller child) {
        addController(child);
        
        addNode(getFieldName(child.getClass().getName()), child.getView());
    }

    protected void addController(Controller child) {
        Field field = getControllerField(getFieldName(child.getClass().getName()));
        if (field == null)
            //ToDo:Throw custom exception
            System.err.println("field:" + child.getClass().getName() + " not found");
        if (!field.getType().equals(child.getClass()))
            //ToDo:Throw custom exception
            System.err.println("field:" + child.getClass().getName() + " type different");
        try {
            field.set(this, child);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static String getFieldName (String className) {
        int idx = className.lastIndexOf('.') + 1;
        return className.substring(idx, className.length());
    }
    
    protected Field getControllerField(String fieldName) {
        for (Field f : getControllerFields()) {
            if (f.getName().equals(fieldName))
                return f;
        }
        return null;
    }

    protected List<Field> getControllerFields() {
        List<Field> fields = new ArrayList<Field>();
        for (Field f : getClass().getDeclaredFields()) {
            for (Annotation an : f.getDeclaredAnnotations())
                if (an instanceof FXController)
                    fields.add(f);
        }
        return fields;
    }

    protected final void addNode(String controllerName, Node node) {
        String nodeId = controllerName.replaceAll("Controller$", "");
        Pane child = getPane(nodeId);
        if (child == null)
            //ToDo:Throw custom exception
            System.err.println("child node:" + nodeId + " not found.");
        child.getChildren().add(node);
    }

    protected Pane getPane(String nodeId) {
        List<Pane> targetPanes = new ArrayList<Pane>();
        findPane(view, nodeId, targetPanes);
        return targetPanes.isEmpty()? null : targetPanes.get(0);
    }
    
    protected static void findPane(Pane pane, String nodeId, List<Pane> targetPanes) {
        for (Node node : pane.getChildren()) {
            if (Pane.class.isAssignableFrom(node.getClass())) {
                if (node.getId() != null && node.getId().equals(nodeId))
                    //ToDo:Type check
                    targetPanes.add((Pane)node);
                else
                    findPane((Pane)node, nodeId, targetPanes);
            }
        }
    }
}
