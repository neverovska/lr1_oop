package Methods.Salary_project;

import Company.Salary_project;
import DB.Const;
import DB.DatabaseHandler;
import Methods.Logi.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Take_Salary_Projects {
    public static ArrayList<Salary_project> Take(String bik) {
        ArrayList<Salary_project> salary_projects = new ArrayList<>();
        ResultSet resSet = null;

        String take = "SELECT * FROM " + Const.SALARY_PROJECT_TABLE + " WHERE " + Const.SALARY_PROJECT_BIK + "=? ";
        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(take);
            prSt.setString(1, bik);

            resSet = prSt.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.SALARY_PROJECT_NAME);
                String passport = resSet.getString(Const.SALARY_PROJECT_PASSPORT);
                double count = resSet.getDouble(Const.SALARY_PROJECT_COUNT);
                String account_name = resSet.getString(Const.SALARY_PROJECT_ACCOUNT_NAME);
                String company = resSet.getString(Const.SALARY_PROJECT_COMPANY);
                boolean approve = resSet.getBoolean(Const.SALARY_PROJECT_APPROVE);

                salary_projects.add(new Salary_project(name, bik, passport, count, account_name,
                        company, approve));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salary_projects;
    }
}
