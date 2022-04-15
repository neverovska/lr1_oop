package sample.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Company.Company;
import Methods.Account.Block_account;
import Methods.Account.Make_Account;
import Methods.Account.Unblock_account;
import Methods.Change;
import Money.Bank_Account;
import Money.Credit;
import Money.Installment_Plan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static sample.Controllers.company_viewController.company;

public class company_viewController {

    static Company company;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem change_passwordMenuitem;

    @FXML
    private Button blockButton;

    @FXML
    private Button unblockButton;

    @FXML
    private MenuItem exitMenuitem;

    @FXML
    private Button make_accountButton;

    @FXML
    private Button make_creditButton;

    @FXML
    private Button make_installment_planButton;

    @FXML
    private Label nameLabel;

    @FXML
    private ComboBox<String> showComboBox;

    @FXML
    private ListView<String> showListView;

    @FXML
    private Button transferButton;

    @FXML
    private TextField countField;

    @FXML
    private Button zpButton;


    @FXML
    void initialize() {
        company = Init_Controller._company;

        ObservableList<String> values = FXCollections.observableArrayList("Счета", "Рассрочки", "Кредиты");
        showComboBox.setItems(values);

        nameLabel.setText(company.getName());

        make_accountButton.setOnAction(event -> {
            showComboBox.setValue("Счета");
            String account_number = Integer.toString(Controller.bank.getAccounts().size());
            try {
                Bank_Account bank_account = new Bank_Account(company.getUnp(), account_number,
                        Double.parseDouble(countField.getText()), Controller.bank.getBik());

                Make_Account.New_Account(bank_account);
                company.getAccounts().add(bank_account);
                Controller.bank.getAccounts().add(bank_account);

                showListView.getItems().clear();
                showListView.getItems().addAll(Make_account_string(company.getAccounts()));

            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
            }

            countField.setText("");
        });

        transferButton.setOnAction(event -> {
            transferButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/transfer_company.fxml");
        });

        showComboBox.setOnAction(event -> {
            if(showComboBox.getValue().equals("Кредиты")) {
                showListView.getItems().clear();
                showListView.getItems().addAll(Make_credit_string(company.getCredits()));

            } else if(showComboBox.getValue().equals("Счета")) {
                showListView.getItems().clear();
                showListView.getItems().addAll(Make_account_string(company.getAccounts()));

            } else if(showComboBox.getValue().equals("Рассрочки")) {
                showListView.getItems().clear();
                 showListView.getItems().addAll(Make_instalmentplan_string(company.getInstallment_plans()));

            }
        });

        make_creditButton.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/make_credit.fxml");
        });

        make_installment_planButton.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/make_installment_plan.fxml");
        });

        exitMenuitem.setOnAction(event -> {
            make_creditButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/init.fxml");
        });

        final String[][] account_name = new String[1][1];
        MultipleSelectionModel<String> langsSelectionModel = showListView.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                if(newValue != null)
                    account_name[0] = newValue.split("\t\t\t");
            }
        });

        blockButton.setOnAction(event -> {
            showListView.getItems().clear();
            Block_account.Block(account_name[0][0]);
            for(Bank_Account ba : company.getAccounts())
                if(ba.getNumber().equals(account_name[0][0]))
                    ba.setBloking(true);

            showListView.getItems().addAll(Make_account_string(company.getAccounts()));

        });

        unblockButton.setOnAction(event -> {
            showListView.getItems().clear();
            Unblock_account.Unblock(account_name[0][0]);
            for(Bank_Account ba : company.getAccounts())
                if(ba.getNumber().equals(account_name[0][0]))
                    ba.setBloking(false);

            showListView.getItems().addAll(Make_account_string(company.getAccounts()));

        });

        zpButton.setOnAction(event -> {
            zpButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/zp_company_view.fxml");
        });
    }
    private String[] Make_account_string(ArrayList<Bank_Account> bank_accounts){
        String[] str = new String[bank_accounts.size()];
        int i = 0;
        for(Bank_Account bank_account : bank_accounts) {
            String tab = "\t\t\t";

            if (!bank_account.getBloking() && !bank_account.getFreezing())
                str[i] =  bank_account.getNumber() + tab + "Активен." + tab + Double.toString(bank_account.getCount());

            else if (bank_account.getBloking())
                str[i] = bank_account.getNumber() + tab + "Заблокироан." + tab + Double.toString(bank_account.getCount());

            else
                str[i] = bank_account.getNumber() + tab + "Заморожен.";

            i++;
        }
        return str;
    }
    private String[] Make_credit_string(ArrayList<Credit> credits) {
        String[] str = new String[credits.size()];
        int i = 0;
        for(Credit credit : credits) {
            String tab = "\t\t\t";

            if (credit.getAllowed())
                str[i] = credit.getNumber() + tab + "Открыт." + tab + Double.toString(credit.getCount());

            else
                str[i] = credit.getNumber() + tab + "В обработке." + tab + Double.toString(credit.getCount());

            i++;
        }
        return str;
    }
    private String[] Make_instalmentplan_string(ArrayList<Installment_Plan> installment_plans){
        String[] str = new String[installment_plans.size()];
        int i = 0;
        for(Installment_Plan installment_plan : installment_plans) {
            String tab = "\t\t\t";

            if (installment_plan.getAllowed())
                str[i] = installment_plan.getNumber() + tab + "Открыт." + tab + Double.toString(installment_plan.getCount());

            else
                str[i] = installment_plan.getNumber() + tab + "В обработке." + tab + Double.toString(installment_plan.getCount());

            i++;
        }
        return str;
    }
}