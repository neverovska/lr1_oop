package sample.Controllers;

import Company.Company;
import Company.Transfer;
import Methods.Account.Take_Account;
import Methods.Change;
import Methods.Logi.MakeTransfer;
import Money.Bank_Account;
import Money.Money_Transfer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class transfer_companyController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> in_accountComboBox;

    @FXML
    private TextField countText;

    @FXML
    private ComboBox<String> out_accountComboBox;

    @FXML
    private Button backButton;

    @FXML
    private Button transferButton;

    @FXML
    void initialize() {
        Company out_company = Init_Controller._company;
        int i = 0;
        String[] account_name = new String[out_company.getAccounts().size()];
        for(Bank_Account account : out_company.getAccounts()){
            account_name[i] = account.getNumber();
            i++;
        }
        ObservableList<String> values = FXCollections.observableArrayList(account_name);
        out_accountComboBox.setItems(values);

        i = 0;
        ArrayList<Bank_Account> bank_accounts = Take_Account.Accounts(null, null);
        String[] accounts = new String[bank_accounts.size()];
        for(Bank_Account account : bank_accounts){
            accounts[i] = account.getNumber();
            i++;
        }
        values = FXCollections.observableArrayList(accounts);
        in_accountComboBox.setItems(values);

        transferButton.setOnAction(event -> {
            try {
                MakeTransfer makeTransfer = new MakeTransfer();
                Bank_Account buf = new Bank_Account(), buf1 = new Bank_Account();
                for (Bank_Account b : bank_accounts)
                    if (b.getNumber().equals(in_accountComboBox.getValue()))
                        buf = b;
                    else if (b.getNumber().equals(out_accountComboBox.getValue()))
                        buf1 = b;

                if (buf1.getCount() >= Double.parseDouble(countText.getText()) && !buf.getBloking() && !buf.getFreezing()) {

                    for(Bank_Account ba : out_company.getAccounts())
                        if(ba.getNumber().equals(buf.getNumber()))
                            ba.setCount(ba.getCount() + Double.parseDouble(countText.getText()));
                        else if(ba.getNumber().equals(buf1.getNumber()))
                            ba.setCount(ba.getCount() - Double.parseDouble(countText.getText()));

                    Money_Transfer.Transfer(buf1, buf, Double.parseDouble(countText.getText()));
                    makeTransfer.Make_log(buf1.getNumber(), buf.getNumber(), Double.parseDouble(countText.getText()), Controller.bank.getBik());
                    Change.Change_View(this, "/sample/View/company_view.fxml");
                    transferButton.getScene().getWindow().hide();
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                countText.setText("");
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
