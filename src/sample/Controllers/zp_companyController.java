package sample.Controllers;

import Company.Company;
import Company.Salary_project;

import Methods.Change;
import Methods.Salary_project.SalaryProjectSend;
import Money.Bank_Account;
import User.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class zp_companyController {
    @FXML
    private ComboBox<String> account_ComboBox;

    @FXML
    private Label company_nameLabel;

    @FXML
    private TextField countField;

    @FXML
    private Button okButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox<String> worker_ComboBox;


    @FXML
    void initialize() {
        Company company = company_viewController.company;

        company_nameLabel.setText(company.getName());

        //"Арсений самый сладкий котик"


        ObservableList<String> values = FXCollections.observableArrayList(Take_names(Controller.bank.getSalary_projects()));
        worker_ComboBox.setItems(values);

        okButton.setOnAction(event -> {
            for(Salary_project sp : Controller.bank.getSalary_projects())
                if(sp.getName().equals(worker_ComboBox.getValue())) {
                    sp.setCount(Double.parseDouble(countField.getText()));
                    sp.setSend(true);
                    SalaryProjectSend.Send(sp.getName(), sp.getCount());
                }
        });

        returnButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
            Change.Change_View(this, "/sample/View/company_view.fxml");
        });
    }
    private String[] Take_names(ArrayList<Salary_project> salary_projects){
        int size = 0;
        for(Salary_project w : salary_projects) {
            System.out.print(w.getName());
            if(!w.getSend() && w.getCompany().equals(company_nameLabel.getText()))
                size++;
        }
        String[] workers_names = new String[size];
        int i = 0;
        for(Salary_project w : salary_projects) {
            if(!w.getSend() && w.getCompany().equals(company_nameLabel.getText())){
                workers_names[i] = w.getName();
                i++;
            }
        }
        return workers_names;
    }
}
