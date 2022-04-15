package Methods.Logi;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MakeTransfer {
    public void Make_log(String number1, String number2, double count, String bik) {
        DatabaseHandler handler = new DatabaseHandler();
        String insert = "INSERT INTO " + Const.LOGI_TABLE + " (" + Const.LOGI_FLAG + "," + Const.LOGI_NUMBER1 + "," +
                Const.LOGI_NUMBER2 + "," + Const.LOGI_COUNT + "," + Const.LOGI_BIK + ")VALUE(?,?,?,?,?)";
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, "-t-");
            prSt.setString(2, number1);
            prSt.setString(3, number2);
            prSt.setDouble(4, count);
            prSt.setString(5, bik);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
