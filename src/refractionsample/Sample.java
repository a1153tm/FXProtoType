/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refractionsample;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author miyabetaiji
 */
public class Sample extends Application {
    
    public static void main(String[] args) {
        Application.launch(Sample.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        // create root instace
        LoginController controller = (LoginController)loadController("Login.fxml");
        
        // get and set childeren
        setChildren(controller);
        
        // set root
        Group root = new Group();
        root.getChildren().add(controller.getView());
               
        // Show view
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    private Object loadController(String url) throws IOException
    {
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = getClass().getResourceAsStream(url);
            byte[] buf = new byte[1024];
            String str = new String(buf);
            System.out.println(str);
            
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        }
        finally
        {
            if (fxmlStream != null)
                fxmlStream.close();
        }
    }
    
    /*
     * Step of controllers and view with recursive
     * (1)create the parent controller instance.
     * (2)parse the parent class. get fields of child controller.
     * (3)create the child controller instance.
     * (4)set the child controller objcect to parent controller field.
     * (5)add the child view objcect to parent view field.
     */
    private Node createView(String url) throws IOException
    {
        Controller parentCntlrObj = (Controller)loadController(url);

        List<Field> childCntlrFields = getChildCntrFields(parentCntlrObj.getClass());
        if (childCntlrFields.isEmpty())
            return parentCntlrObj.getView();

        for (Iterator it = childCntlrs.iterator(); it.hasNext(); ) {
            Field childCntlr = (Field)it.next();
            Node child = createView();
            addChild(child);
        }
    }

    private List<Field> getChildCntrFields(Class<?> theClass)
    {
        List<Field> childCntlrFields = new ArrayList<Field>();

        for (Field f : theClass.getDeclaredFields()) {
            if (f.getName().endsWith("Controller"))
                childCntlrFields.add(f);
        }
        return childCntlrFields;
    }

    private String getUrl(Field field)
    {
        String childViewName = childCntlr.getName().replaceAll("Controller$", "");
    }

    private Srting get

    private String get
    private void setChildren(Object parentCntlrObj) throws IOException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Class<?> theClass = parentCntlrObj.getClass();

        for (Iterator it = childCntlrs.iterator(); it.hasNext(); ) {
            Field childCntlr = (Field)it.next();
            String childViewName = childCntlr.getName().replaceAll("Controller$", "");
            // create child instance and set to parent filed
            String childUrl = childViewName + ".fxml";
            Object childCntlrObj = loadController(childUrl);
            childCntlr.set(parentCntlrObj, childCntlrObj);
            // get child view and set to parend filed
            Method getView = childCntlrObj.getClass().getMethod("getView", null);
            Node childNode = (Node) getView.invoke(childCntlrObj, null);
            Method setView = theClass.getMethod("set" + childViewName , new Class[]{ Node.class });
            setView.invoke(parentCntlrObj, new Object[]{ childNode });
        }
    }
    
    private void showFields(Class<?> theClass) {
        for (Field f : theClass.getDeclaredFields()) {
            System.out.println(f.getName());
        }
    }
}
