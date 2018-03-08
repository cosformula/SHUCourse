import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Welcome {
    @FXML
    Label name;
    public void initialize(){
        name.setText(Main.student.getValue().getName());
    }

}
