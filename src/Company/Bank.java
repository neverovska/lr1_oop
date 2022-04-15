package Company;

import Init.Administrator_Init;
import Init.Company_Init;
import Init.Manager_Init;
import Init.Operator_Init;
import Methods.Account.Take_Account;
import Methods.Client.All_Clients_From_Sql;
import Methods.Client.Client_Init;
import Methods.Contribution.ContributionTake;
import Methods.Credit.Credit_From_Sql;
import Methods.Installment_Plan.Installment_Plan_From_Sql;
import Methods.Logi.Log;
import Methods.Logi.Take;
import Methods.Salary_project.Take_Salary_Projects;
import Money.Bank_Account;
import Money.Contribution;
import Money.Credit;
import Money.Installment_Plan;
import User.Administrator;
import User.Client;
import User.Manager;
import User.Operator;

import java.util.ArrayList;

public class Bank extends Company{
    private ArrayList<Client> clients;
    private ArrayList<Bank_Account> accounts;
    private ArrayList<Credit> credits;
    private ArrayList<Installment_Plan> installment_plans;
    private ArrayList<Operator> operators;
    private ArrayList<Administrator> administrators;
    private ArrayList<Manager> managers;
    private ArrayList<Company> companies;
    private ArrayList<Log> logs;
    private ArrayList<Salary_project> salary_projects;
    private ArrayList<Transfer> transfers;
    private ArrayList<Contribution> contributions;

    public Bank(String type, String name, String unp, String bank_BIK, String adres, String password) {
        super(type, name, unp, bank_BIK, adres, password);
        Take_Accounts(bank_BIK);
        Take_Administrators(bank_BIK);
        Take_contribution(bank_BIK);
        Take_Credits(bank_BIK);
        Take_Installment_Plans(bank_BIK);
        Take_Managers(bank_BIK);
        Take_Operators(bank_BIK);
        Take_Clients(bank_BIK);
        Take_Companies(bank_BIK);
        Take_Salary_Projects(bank_BIK);
        Take_Logs(bank_BIK);
        transfers = new ArrayList<>();
    }



    public ArrayList<Client> getClients() { return clients; }
    public void setClients(ArrayList<Client> clients) { this.clients = clients; }

    public ArrayList<Bank_Account> getAccounts() { return accounts; }
    public void setAccounts(ArrayList<Bank_Account> accounts) { this.accounts = accounts; }

    public ArrayList<Credit> getCredits() { return credits; }
    public void setCredits(ArrayList<Credit> credits) { this.credits = credits; }

    public ArrayList<Installment_Plan> getInstallment_plans() { return installment_plans; }
    public void setInstallment_plans(ArrayList<Installment_Plan> installment_plans) { this.installment_plans = installment_plans; }

    public ArrayList<Operator> getOperators() { return operators; }
    public void setOperators(ArrayList<Operator> operators) { this.operators = operators; }

    public ArrayList<Administrator> getAdministrators() { return administrators; }
    public void setAdministrators(ArrayList<Administrator> administrators) { this.administrators = administrators; }

    public ArrayList<Manager> getManagers() { return managers; }
    public void setManagers(ArrayList<Manager> managers) { this.managers = managers; }

    public ArrayList<Company> getCompany() { return companies; }
    public void setCompany(ArrayList<Company> companies) { this.companies = companies; }

    public ArrayList<Salary_project> getSalary_projects() {
        return salary_projects;
    }

    public ArrayList<Log> getLogs() { return logs; }

    public ArrayList<Transfer> getTransfers() {
        return transfers;
    }

    private void Take_Clients(String bik){
        clients = Client_Init.Init(bik);
        for(Client cl : clients) {
            for (Bank_Account ba : accounts)
                if (cl.getPassport().equals(ba.getClientpass()))
                    cl.getAccounts().add(ba);

            for (Installment_Plan ip : installment_plans)
                if (cl.getPassport().equals(ip.getPassport()))
                    cl.getInstallment_plans().add(ip);

            for (Credit credit : credits)
                if (cl.getPassport().equals(credit.getPassport()))
                    cl.getCredits().add(credit);

            for (Contribution cn : contributions)
                if(cl.getPassport().equals(cn.getClientpass()))
                    cl.getContributions().add(cn);
        }
    }
    private void Take_Accounts(String bik){
        accounts = Take_Account.Accounts(null, bik);
    }
    private void Take_Credits(String bik){
        credits = Credit_From_Sql.Take(null, bik);
    }
    private void Take_Installment_Plans(String bik){
        installment_plans = Installment_Plan_From_Sql.Take(null, bik);
    }
    private void Take_Operators(String bik){
        operators = Operator_Init.Init(bik);
    }
    private void Take_Managers(String bik){
        managers = Manager_Init.Init(bik);
    }
    private void Take_Administrators(String bik){
        administrators = Administrator_Init.Init(bik);
    }
    private void Take_Companies(String bik) {
        companies = Company_Init.Init(bik);

        for(Company co : companies) {
            for (Bank_Account ba : accounts)
                if (co.getUnp().equals(ba.getClientpass()))
                    co.getAccounts().add(ba);

            for (Installment_Plan ip : installment_plans)
                if (co.getUnp().equals(ip.getPassport()))
                    co.getInstallment_plans().add(ip);

            for (Credit credit : credits)
                if (co.getUnp().equals(credit.getPassport()))
                    co.getCredits().add(credit);

            for (Client cl : clients)
                if(co.getName().equals(cl.getCompany()))
                    co.getWorkers().add(cl);

            for (Contribution cn : contributions)
                if(co.getUnp().equals(cn.getClientpass()))
                    co.getContributions().add(cn);
        }
    }
    private void Take_Salary_Projects(String bik) {
        salary_projects = Take_Salary_Projects.Take(bik);
    }
    private void Take_Logs(String bik){
        logs = Take.Take_logs(bik);
    }
    private void Take_contribution(String bik) { contributions = ContributionTake.Take(null, bik); }
}
