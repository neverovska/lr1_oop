package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import Company.Company;
import Methods.Change;
import Registration.Client_registration;
import Registration.Company_registration;
import User.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class user_registrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private CheckBox companyCheckBox;

    @FXML
    private URL location;

    @FXML
    private TextField TextField_country;

    @FXML
    private TextField TextField_email;

    @FXML
    private TextField TextField_id;

    @FXML
    private TextField TextField_name;

    @FXML
    private TextField companyField;

    @FXML
    private TextField TextField_passport;

    @FXML
    private TextField TextField_phonenumber;

    @FXML
    private PasswordField password;

    @FXML
    private Button buttonReturn;

    @FXML
    private Button ButtonRegistration;

    @FXML
    private PasswordField password_rep;

    @FXML
    void initialize() {

        companyCheckBox.setOnAction(event -> {
            if(companyCheckBox.isSelected()){
                TextField_name.setPromptText("Название");
                TextField_passport.setPromptText("Тип");
                TextField_id.setPromptText("УНП");
                TextField_phonenumber.setDisable(true);
                TextField_country.setDisable(true);
                companyField.setDisable(true);
            }
            else {
                TextField_name.setPromptText("ФИО");
                TextField_passport.setPromptText("Паспортные данные");
                TextField_id.setPromptText("Серия и номер паспорта");
                TextField_phonenumber.setDisable(false);
                TextField_country.setDisable(false);
                companyField.setDisable(false);
            }
        });

        ButtonRegistration.setOnAction(event -> {
            if (password.getText().equals(password_rep.getText())){
                if(!companyCheckBox.isSelected()) {
                    Client_registration.signUpClient(new Client(TextField_name.getText(), TextField_passport.getText(),
                            TextField_id.getText(), TextField_phonenumber.getText(), TextField_email.getText(), Controller.bank.getBik(),
                            password.getText(), TextField_country.getText(), companyField.getText()), Controller.bank.getBik());
                    Controller.bank.getClients().add(new Client(TextField_name.getText(), TextField_passport.getText(),
                            TextField_id.getText(), TextField_phonenumber.getText(), TextField_email.getText(), Controller.bank.getBik(),
                            password.getText(), TextField_country.getText(), companyField.getText()));
                } else {
                    Company_registration.Registration(new Company(TextField_passport.getText(), TextField_name.getText(),
                            TextField_id.getText(), Controller.bank.getBik(), TextField_email.getText(), password.getText()));
                    Controller.bank.getCompany().add(new Company(TextField_passport.getText(), TextField_name.getText(),
                            TextField_id.getText(), Controller.bank.getBik(), TextField_email.getText(), password.getText()));
                }
                ButtonRegistration.getScene().getWindow().hide();
                Change.Change_View(this, "/sample/View/init.fxml");
            }
            else {
                password_rep.setStyle("-fx-border-color: red" );
            }


        });

        buttonReturn.setOnAction(event -> {
            buttonReturn.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });
    }
}