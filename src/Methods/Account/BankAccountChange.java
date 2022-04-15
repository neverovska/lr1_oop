package Methods.Account;


import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountChange {
    public static void Change(String number, double count) {
        String update = "UPDATE " + Const.BANKACCOUNT_TABLE + " SET " + Const.BANKACCOUNT_COUNT + "=? " + " WHERE " +
                Const.CONTRIBUTION_NUMBER + "=?";
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(update);
            prSt.setDouble(1, count);
            prSt.setString(2, number);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
