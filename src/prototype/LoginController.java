package prototype;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LoginController extends Controller {
    
    @FXML
    private AnchorPane view;
            
    @FXML
    public AnchorPane UserType;
    
    @FXController
    public UserTypeController UserTypeController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
       
    public void setUserType(Node node) {
        UserType.getChildren().add(node);
    }
}
