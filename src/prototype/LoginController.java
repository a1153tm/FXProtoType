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
    private AnchorPane UserType;
    
    @FXML
    private AnchorPane InputBoxes;
    
    @FXML
    private AnchorPane CommandButtons;
    
    @FXController
    public UserTypeController UserTypeController;
    
    @FXController
    public InputBoxesController InputBoxesController;

    @FXController
    public CommandButtonsController CommandButtonsController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
