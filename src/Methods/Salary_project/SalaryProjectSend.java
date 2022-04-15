package Methods.Salary_project;

import DB.Const;
import DB.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalaryProjectSend {
    public static void Send(String name, double count) {
        String update = "UPDATE " + Const.SALARY_PROJECT_TABLE + " SET " + Const.SALARY_PROJECT_SEND + " =? AND " +
                Const.SALARY_PROJECT_COUNT + "=? WHERE " + Const.SALARY_PROJECT_NAME + "=?";
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(update);
            prSt.setBoolean(1, true);
            prSt.setDouble(2, count);
            prSt.setString(3, name);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
