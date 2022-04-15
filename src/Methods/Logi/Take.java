package Methods.Logi;

import Company.Company;
import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Take {
    public static ArrayList<Log> Take_logs(String bik) {
        ArrayList<Log> logs = new ArrayList<>();
        ResultSet resSet = null;

        String take = "SELECT * FROM " + Const.LOGI_TABLE + " WHERE " + Const.LOGI_BIK + "=? ";
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String flag = resSet.getString(Const.LOGI_FLAG);
                String number1 = resSet.getString(Const.LOGI_NUMBER1);
                String number2 = resSet.getString(Const.LOGI_NUMBER2);
                double count = resSet.getDouble(Const.LOGI_COUNT);

                logs.add(new Log(flag ,number1, bik, number2, count));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
