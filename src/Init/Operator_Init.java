package Init;

import DB.Const;
import DB.DatabaseHandler;
import User.Client;
import User.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operator_Init {
    public static ArrayList<Operator> Init (String bik){
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();




        ArrayList<Operator> operators = new ArrayList<>();
        String select = "SELECT * FROM " + Const.OPERATORS_TABLE + " WHERE " +
                Const.OPERATORS_BIK + "=?";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.OPERATORS_NAME );
                String password = resSet.getString(Const.OPERATORS_PASSWORD);
                String passport = resSet.getString(Const.OPERATORS_PASSPORT);
                String phone_number = resSet.getString(Const.OPERATORS_PHONENUMBER);
                String email = resSet.getString(Const.OPERATORS_EMAIL);
                String id = resSet.getString(Const.OPERATORS_ID);

                operators.add(new Operator(name, passport, id, phone_number, email, bik, password));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return operators;
    }
}

