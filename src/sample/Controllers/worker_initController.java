package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Company.Bank;
import Init.Administrator_Init;
import Init.Manager_Init;
import Init.Operator_Init;
import Methods.Change;
import User.Administrator;
import User.Manager;
import User.Operator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static Methods.Change.Change_View;

public class worker_initController {

    @FXML
    private ResourceBundle resources;

    static Operator operator;
    static Administrator administrator;
    static Manager manager;

    @FXML
    private Button backButton;

    @FXML
    private Label errLabel;

    @FXML
    private URL location;

    @FXML
    private TextField pasportField;

    @FXML
    private PasswordField password;

    @FXML
    private ComboBox<String> rangComboBox;

    @FXML
    private Button initButton;

    @FXML
    void initialize() {
        operator = null;
        administrator = null;
        manager = null;
        Bank bank = Controller.bank;
        initButton.setDisable(true);
        ObservableList<String> values = FXCollections.observableArrayList("Оператор", "Администратор", "Менеджер");
        rangComboBox.setItems(values);
        rangComboBox.setOnAction(event -> {
            initButton.setDisable(false);
            pasportField.setEditable(true);
            password.setEditable(true);
        });

        initButton.setOnAction(event -> {
            String passport = pasportField.getText();
            String pass = password.getText();
            if(passport.equals("") || pass.equals("")){
                errLabel.setText("Поля должны быть заполнены.");
            } else {
                switch (rangComboBox.getValue()) {
                    case "Оператор":
                        for (Operator op : bank.getOperators()) {
                            if (op.getPassport().equals(pasportField.getText()) && op.getPassword().equals(password.getText())) {
                                operator = op;
                                initButton.getScene().getWindow().hide();
                                Change_View(this, "/sample/View/operator_view.fxml");
                            }
                        }
                        errLabel.setText("Такого клиента не существует.");
                        break;
                    case "Администратор":
                        for (Administrator ad : bank.getAdministrators()) {
                            if (ad.getPassport().equals(pasportField.getText()) && ad.getPassword().equals(password.getText())) {
                                administrator = ad;
                                initButton.getScene().getWindow().hide();
                                Change_View(this, "/sample/View/administrator_view.fxml");
                            }
                        }
                        errLabel.setText("Такого клиента не существует.");
                        break;
                    case "Менеджер":
                        for (Manager mn : bank.getManagers()) {
                            if (mn.getPassport().equals(pasportField.getText()) && mn.getPassword().equals(password.getText())) {
                                manager = mn;
                                initButton.getScene().getWindow().hide();
                                Change_View(this, "/sample/View/manager_view.fxml");
                            }
                        }
                        errLabel.setText("Такого клиента не существует.");
                        break;
                }
            }
        });
        backButton.setOnAction(event -> {
            Change.Change_View(this, "/sample/View/Init.fxml");
            backButton.getScene().getWindow().hide();
        });
    }

}
