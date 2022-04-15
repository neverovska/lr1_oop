package Methods.Contribution;

import DB.Const;
import DB.DatabaseHandler;
import Money.Contribution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContributionTake {
    public static ArrayList<Contribution> Take(String passport, String bik) {
        ArrayList<Contribution> contributions = new ArrayList<Contribution>();
        ResultSet resSet = null;
        DatabaseHandler handler = new DatabaseHandler();
        try {
            if(passport != null && bik != null) {//вернуть список всех счетов одного клиента в одном банке
                String select = "SELECT * FROM " + Const.CONTRIBUTION_TABLE + " WHERE " +
                        Const.CONTRIBUTION_USERPASS + "=? AND " + Const.CONTRIBUTION_BIK + "=?";

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, passport);
                prSt.setString(2, bik);
                resSet = prSt.executeQuery();

            } else if(bik != null) {//вернуть список всех сетов банка
                String select = "SELECT * FROM " + Const.CONTRIBUTION_TABLE + " WHERE " +
                        Const.CONTRIBUTION_BIK + "=?";

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                prSt.setString(1, bik);
                resSet = prSt.executeQuery();

            }else {//вернуть список вообще ВСЕХ счетов
                String select = "SELECT * FROM " + Const.CONTRIBUTION_TABLE;

                PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
                resSet = prSt.executeQuery();
            }



            while (resSet.next()) {
                String name = resSet.getString(Const.CONTRIBUTION_NUMBER);
                double count = resSet.getDouble(Const.CONTRIBUTION_COUNT);
                boolean bloking = resSet.getBoolean(Const.CONTRIBUTION_BLOСKING);
                String _passport = resSet.getString(Const.CONTRIBUTION_USERPASS);
                boolean freezing = resSet.getBoolean(Const.CONTRIBUTION_FREEZING);
                double percent = resSet.getDouble(Const.CONTRIBUTION_PERCENT);
                contributions.add(new Contribution(_passport, name, count, bloking, freezing, bik, percent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contributions;
    }
}
