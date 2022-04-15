package Methods.Account;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountDelete {
    public static void Delete(String number) {
        DatabaseHandler handler = new DatabaseHandler();
        String delete = "DELETE FROM " + Const.CONTRIBUTION_TABLE + " WHERE " + Const.CONTRIBUTION_NUMBER + "=?";

        PreparedStatement prSt = null;
        try {
            prSt = handler.getDbConnection().prepareStatement(delete);

            prSt.setString(1, number);

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
