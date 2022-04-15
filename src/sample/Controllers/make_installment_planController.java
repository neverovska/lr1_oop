package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Methods.Change;
import Methods.Installment_Plan.Installment_Plan_To_Sql;
import Money.Credit;
import Money.Installment_Plan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class make_installment_planController {

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
    private Label nameLabel;

    @FXML
    void initialize() {
        String name = String.valueOf(Controller.bank.getInstallment_plans().size());

        ObservableList<String> values = FXCollections.observableArrayList("3", "6", "12", "24", "48");
        monthComboBox.setItems(values);



        addButton.setOnAction(event -> {
            try {
                if (Init_Controller._company == null) {
                    int month = Integer.parseInt(monthComboBox.getValue());
                    Installment_Plan installment_plan = new Installment_Plan(name, month,
                            Init_Controller._client.getPassport(), Controller.bank.getBik(), Double.parseDouble(countField.getText()));

                    Init_Controller._client.getInstallment_plans().add(installment_plan);
                    Installment_Plan_To_Sql.Make(installment_plan);

                    Change.Change_View(this, "/sample/View/user_view.fxml");

                } else {
                    int month = Integer.parseInt(monthComboBox.getValue());
                    Installment_Plan installment_plan = new Installment_Plan(name, month,
                            Init_Controller._company.getUnp(), Controller.bank.getBik(), Double.parseDouble(countField.getText()));

                    Init_Controller._company.getInstallment_plans().add(installment_plan);
                    Installment_Plan_To_Sql.Make(installment_plan);

                    Change.Change_View(this, "/sample/View/company_view.fxml");
                }
                addButton.getScene().getWindow().hide();
            } catch (Exception e) {
                countField.setText("");
                Change.Change_View(this, "/sample/View/message_box.fxml");
            }

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
