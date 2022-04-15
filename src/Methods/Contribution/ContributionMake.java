package Methods.Contribution;

import DB.Const;
import DB.DatabaseHandler;
import Money.Contribution;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContributionMake {
    public static void Make(Contribution contribution){
        String insert = "INSERT INTO " + Const.CONTRIBUTION_TABLE + "(" + Const.CONTRIBUTION_USERPASS + "," +
                Const.CONTRIBUTION_NUMBER + "," + Const.CONTRIBUTION_COUNT + "," + Const.CONTRIBUTION_BLOСKING + "," +
                Const.CONTRIBUTION_FREEZING + "," + Const.CONTRIBUTION_BIK + "," +
                Const.CONTRIBUTION_PERCENT + ")" + "VALUES(?,?,?,?,?,?,?)";

        DatabaseHandler handler = new DatabaseHandler();
        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(insert);
            prSt.setString(1, contribution.getClientpass());
            prSt.setString(2, contribution.getNumber());
            prSt.setDouble(3, contribution.getCount());
            prSt.setBoolean(4, contribution.getBloking());
            prSt.setBoolean(5, contribution.getFreezing());
            prSt.setString(6, contribution.getBik());
            prSt.setDouble(7, contribution.getPerсent());

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
