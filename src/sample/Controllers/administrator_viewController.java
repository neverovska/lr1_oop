package sample.Controllers;

import Company.Salary_project;

import Methods.Account.Take_Account;
import Methods.Change;
import Methods.Logi.Log;
import Methods.Logi.LogDelete;
import Money.Bank_Account;
import Money.Money_Transfer;
import User.Administrator;
import User.Operator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class administrator_viewController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button cancellation;
    

    @FXML
    private MenuItem exit;

    @FXML
    private Label nameLable;

    @FXML
    private ListView<String> showList;

    @FXML
    void initialize() {
       Administrator administrator = worker_initController.administrator;
        nameLable.setText(administrator.getName());

        ArrayList<Log> logs = Controller.bank.getLogs();

        showList.getItems().clear();
        showList.getItems().addAll(logs_string(logs));

        final String[] name = new String[1];
        MultipleSelectionModel<String> langsSelectionModel = showList.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                name[0] = newValue;
            }
        });
        cancellation.setOnAction(event -> {
            if (name[0] != null) {
                ArrayList<Bank_Account> bank_accounts = Take_Account.Accounts(null, null);
                String[] str = name[0].split("\t\t\t");
                Bank_Account buf = new Bank_Account(), buf1 = new Bank_Account();

                for (Bank_Account b : bank_accounts) {
                    if (b.getNumber().equals(str[0]))
                        buf = b;
                    else if (b.getNumber().equals(str[1]))
                        buf1 = b;
                }
                try {
                    Money_Transfer.Transfer(buf1, buf, Double.parseDouble(str[2]));
                    for (Log log : logs)
                        if (log.getNumber1().equals(buf.getNumber()) && log.getNumber2().equals(buf1.getNumber()) && log.getCount() == Double.parseDouble(str[2])) {
                            logs.remove(log);
                            LogDelete.Delete(log);
                            break;
                        }
                    showList.getItems().clear();
                    showList.getItems().addAll(logs_string(logs));
                } catch (NumberFormatException e) {
                    Change.Change_View(this, "/sample/View/message_box.fxml");
                }
            }
        });

        exit.setOnAction(actionEvent -> {
            nameLable.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });
    }
    private String[] logs_string(ArrayList<Log> logs) {
        int size = 0;
        for(Log l : logs)
            if (l.getFlag().equals("-t-")) {
                size++;
            }
        int i = 0;
        String[] out = new String[size];
        for (Log l : logs) {
            if (l.getFlag().equals("-t-")) {
                out[i] = l.getNumber1() + "\t\t\t" + l.getNumber2() + "\t\t\t" + l.getCount();
                i++;
            }
        }
        return out;
    }
}
