import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Welcome {
    @FXML
    Label name;
    @FXML
    WebView webview;
    public void initialize(){
//        name.setText(Main.student.getValue().getName());
        WebEngine webEngine = webview.getEngine();
        webEngine.load("https://www.shuhelper.cn");
    }

}
