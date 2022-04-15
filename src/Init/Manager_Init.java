package Init;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import User.Manager;
import User.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager_Init {
    public static ArrayList<Manager> Init(String bik){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        ArrayList<Manager> managers = new ArrayList<>();
        String select = "SELECT * FROM " + Const.MANAGER_TABLE + " WHERE " +
                Const.MANAGER_BIK + "=?";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.MANAGER_NAME );
                String password = resSet.getString(Const.MANAGER_PASSWORD);
                String passport = resSet.getString(Const.MANAGER_PASSPORT);
                String phone_number = resSet.getString(Const.MANAGER_PHONENUMBER);
                String email = resSet.getString(Const.MANAGER_EMAIL);
                String id = resSet.getString(Const.MANAGER_ID);

                managers.add(new Manager(name, passport, id, phone_number, email, bik, password));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return managers;
    }
}
