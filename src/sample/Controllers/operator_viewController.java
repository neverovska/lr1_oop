package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Company.Salary_project;
import Methods.Account.Take_Account;
import Methods.Change;
import Methods.Logi.Log;
import Methods.Logi.LogDelete;
import Methods.Salary_project.Approve_salary_project;
import Methods.Salary_project.Salary_project_from_sql;
import Money.Bank_Account;
import Money.Money_Transfer;
import User.Client;
import User.Operator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class operator_viewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button approveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cancellation;

    @FXML
    private ComboBox<String> chComboBox;

    @FXML
    private MenuItem exit;

    @FXML
    private Label nameLable;

    @FXML
    private ListView<String> showList;

    @FXML
    void initialize() {
        approveButton.setDisable(true);
        cancellation.setDisable(true);
        cancelButton.setDisable(true);
        Operator operator = worker_initController.operator;
        nameLable.setText(operator.getName());
        ArrayList<Salary_project> salary_projects = Controller.bank.getSalary_projects();
        ArrayList<Log> logs = Controller.bank.getLogs();

        int i = 0;
        String[] names = new String[salary_projects.size()];
        for (Salary_project sp : salary_projects) {
            if(!sp.getApprove()) {
                names[i] = sp.getName();
                i++;
            }
        }
        chComboBox.setOnAction(event -> {
            if (chComboBox.getValue().equals("Заявки")) {
                showList.getItems().clear();
                showList.getItems().addAll(clients_string(salary_projects));
                approveButton.setDisable(false);
                cancellation.setDisable(true);
                cancelButton.setDisable(false);
            } else if (chComboBox.getValue().equals("Действия")) {
                showList.getItems().clear();
                showList.getItems().addAll(logs_string(logs));
                approveButton.setDisable(true);
                cancellation.setDisable(false);
                cancelButton.setDisable(true);
            }
        });

        ObservableList<String> values = FXCollections.observableArrayList("Действия", "Заявки");
        chComboBox.setItems(values);


        exit.setOnAction(actionEvent -> {
            nameLable.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });

        final String[] name = new String[1];
        MultipleSelectionModel<String> langsSelectionModel = showList.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {
                name[0] = newValue;
            }
        });

        approveButton.setOnAction(event -> {
            for (Salary_project sp : salary_projects) {
                if (sp.getName().equals(name[0])) {
                    Approve_salary_project.Approve(name[0]);
                    sp.setApprove(true);

                    showList.getItems().clear();
                    showList.getItems().addAll(clients_string(salary_projects));
                    break;
                }
            }
        });

        cancellation.setOnAction(event -> {
            ArrayList<Bank_Account> bank_accounts = Take_Account.Accounts(null, null);
            String[] str = name[0].split("\t\t\t");
            Bank_Account buf = new Bank_Account(), buf1 = new Bank_Account();

            for (Bank_Account b : bank_accounts){
                if (b.getNumber().equals(str[0]))
                    buf = b;
                else if (b.getNumber().equals(str[1]))
                    buf1 = b;
            }
            try {
                Money_Transfer.Transfer(buf1, buf, Double.parseDouble(str[2]));
                for(Log log : logs)
                    if(log.getNumber1().equals(buf.getNumber()) && log.getNumber2().equals(buf1.getNumber()) && log.getCount() == Double.parseDouble(str[2])){
                        logs.remove(log);
                        LogDelete.Delete(log);
                        break;
                    }
                showList.getItems().clear();
                showList.getItems().addAll(logs_string(logs));
            } catch (NumberFormatException e) {
                Change.Change_View(this, "/sample/View/message_box.fxml");
            }

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
    private String[] clients_string(ArrayList<Salary_project> clients) {
        int size = 0;
        for (Salary_project l : clients)
            if (!l.getApprove()) {
                size++;
            }
        int i = 0;
        String[] out = new String[size];
        for (Salary_project l : clients)
            if (!l.getApprove()) {
                out[i] = l.getName();
                i++;
            }
        return out;
    }
}
