package refractionsample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class UserTypeController implements Controller {
    @FXML
    private Node view;
    
    @FXML
    private RadioButton radioGuest;
    
    @FXML
    private RadioButton radioUser;
    
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initRadios();
    }
    
    @Override
    public Node getView() {
        return view;
    }

    public boolean guestSelectted() {
        return radioGuest.isSelected();
    }
    
    public boolean userSelectted() {
        return radioUser.isSelected();
    }
    
    private void initRadios() {
        final ToggleGroup group = new ToggleGroup();
        radioGuest.setToggleGroup(group);
        radioUser.setToggleGroup(group);
        radioUser.setSelected(true);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    //label.setText(group.getSelectedToggle().getUserData().toString());
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    //System.out.println(group.getSelectedToggle().selectedProperty().getName());
                } else {
                    System.out.println("toggle is null");
                }
            }
        });
    }
}
