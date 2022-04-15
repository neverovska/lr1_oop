package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Methods.Change;
import Methods.Credit.Credit_To_Sql;
import Money.Credit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class make_creditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField countField;

    @FXML
    private ComboBox<String> monthComboBox;


    @FXML
    private ComboBox<String> percentComboBox;

    @FXML
    void initialize() {
        String name = String.valueOf(Controller.bank.getCredits().size());


        ObservableList<String> values = FXCollections.observableArrayList("3", "6", "12", "24", "48");
        monthComboBox.setItems(values);


        ObservableList<String> _values = FXCollections.observableArrayList("0.3", "0.6", "0.5");
        percentComboBox.setItems(_values);


        addButton.setOnAction(event -> {

            try {
                int month = Integer.parseInt(monthComboBox.getValue());
                double percent = Double.parseDouble(percentComboBox.getValue());

                if (Init_Controller._company == null) {
                    Credit credit = new Credit(name, Double.parseDouble(countField.getText()), month,
                            Init_Controller._client.getPassport(), percent, Controller.bank.getBik());
                    Init_Controller._client.getCredits().add(credit);
                    Credit_To_Sql.Make(credit);

                    Change.Change_View(this, "/sample/View/user_view.fxml");
                } else {
                    Credit credit = new Credit(name, Double.parseDouble(countField.getText()), month,
                            Init_Controller._company.getUnp(), percent, Controller.bank.getBik());
                    Init_Controller._company.getCredits().add(credit);
                    Credit_To_Sql.Make(credit);

                    Change.Change_View(this, "/sample/View/company_view.fxml");
                }
            } catch (Exception e) {
                countField.setText("");
                Change.Change_View(this, "/sample/View/message_box.fxml");
            }
            addButton.getScene().getWindow().hide();
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            if(Init_Controller._company == null)
                Change.Change_View(this, "/sample/View/user_view.fxml");
            else
                Change.Change_View(this, "/sample/View/company_view.fxml");
        });
    }

}
