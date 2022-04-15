package Methods.Logi;

import Company.Salary_project;
import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDelete {
    public static void Delete(Log log) {
        DatabaseHandler handler = new DatabaseHandler();
        String delete = "DELETE FROM " + Const.LOGI_TABLE + " WHERE " + Const.LOGI_NUMBER1 + "=? AND "
                + Const.LOGI_NUMBER2 + "=? AND " + Const.LOGI_COUNT + "=?";

        PreparedStatement prSt = null;
        try {
            prSt = handler.getDbConnection().prepareStatement(delete);

            prSt.setString(1, log.getNumber1());
            prSt.setString(2, log.getNumber2());
            prSt.setDouble(3, log.getCount());

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
