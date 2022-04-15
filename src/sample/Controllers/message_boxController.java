package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class message_boxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ok_button;

    @FXML
    void initialize() {

        ok_button.setOnAction(event -> {
            ok_button.getScene().getWindow().hide();
        });
    }

}
