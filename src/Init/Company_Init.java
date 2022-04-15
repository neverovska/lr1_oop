package Init;

import Company.Bank;
import Company.Company;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import sample.Controllers.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Company_Init {
    public static ArrayList<Company> Init(String bik){
        ArrayList<Company> companies = new ArrayList<Company>();
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.COMPANY_TABLE + " WHERE " + Const.COMPANY_BIK + "=? ";
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (resSet.next()) {
                String name = resSet.getString(Const.COMPANY_NAME);
                String unp = resSet.getString(Const.COMPANY_UNP);
                String adres = resSet.getString(Const.BANKS_ADRES);
                String type = resSet.getString(Const.COMPANY_TYPE);
                String password = resSet.getString(Const.COMPANY_PASSWORD);

                companies.add(new Company(type ,name ,unp, bik, adres, password));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companies;
    }
}
