package sample.Controllers;

import Company.Bank;
import Company.Company;
import Init.Company_Init;
import User.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static Methods.Change.Change_View;


public class Init_Controller {
    public static Client _client;
    public static Company _company;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errLabel;

    @FXML
    private Button Button_init;

    @FXML
    private Button Button_registration;

    @FXML
    private CheckBox companyCheckBox;

    @FXML
    private Button im_WorkerButton;

    @FXML
    private TextField passport_init;

    @FXML
    private PasswordField password;


    @FXML
    void initialize() {
        Bank bank = Controller.bank;
        _company = null;
        _client = null;
        im_WorkerButton.setOnAction(event -> {
            im_WorkerButton.getScene().getWindow().hide();
            Change_View(this, "/sample/View/worker_init.fxml");
        });
        companyCheckBox.setOnAction(event -> {
            if (companyCheckBox.isSelected())
                passport_init.setPromptText("УНП");
            else
                passport_init.setPromptText("Номер паспорта");
        });

        Button_registration.setOnAction(event -> {

            Button_registration.getScene().getWindow().hide();

            Change_View(this, "/sample/View/user_registration.fxml");
        });

        Button_init.setOnAction(event -> {
            if (!companyCheckBox.isSelected()) {
                boolean ch = false;
                for (Client cl : bank.getClients()) {
                    if (cl.getPassport().equals(passport_init.getText()) && cl.getPassword().equals(password.getText())) {
                        if (cl.getApprove()) {
                            _client = cl;
                            Button_registration.getScene().getWindow().hide();
                            Change_View(this, "/sample/View/user_view.fxml");
                        } else {
                            errLabel.setText("Требуется подтверждение.");
                            ch = true;
                        }
                        break;
                    }
                }
                if(ch)
                    errLabel.setText("Такого клиента не существует.");
            } else {
                boolean ch = false;
                for (Company cp : bank.getCompany()) {
                    if (cp.getUnp().equals(passport_init.getText()) && cp.getPassword().equals(password.getText())) {
                        _company = cp;
                        Button_registration.getScene().getWindow().hide();
                        Change_View(this, "/sample/View/company_view.fxml");
                    }
                }
                errLabel.setText("Такого клиента не существует.");
            }

        });
    }
}
