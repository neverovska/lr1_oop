package Init;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Administrator;
import User.Client;
import User.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrator_Init {
    public static ArrayList<Administrator> Init(String bik){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        ArrayList<Administrator> administrators = new ArrayList<>();
        String select = "SELECT * FROM " + Const.ADMINISTRATORS_TABLE + " WHERE " +
                Const.ADMINISTRATORS_BIK + "=?";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.ADMINISTRATORS_NAME );
                String password = resSet.getString(Const.ADMINISTRATORS_PASSWORD);
                String passport = resSet.getString(Const.ADMINISTRATORS_PASSPORT);
                String phone_number = resSet.getString(Const.ADMINISTRATORS_PHONENUMBER);
                String email = resSet.getString(Const.ADMINISTRATORS_EMAIL);
                String id = resSet.getString(Const.ADMINISTRATORS_ID);

                administrators.add(new Administrator(name, passport, id, phone_number, email, bik, password));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return administrators;
    }
}
