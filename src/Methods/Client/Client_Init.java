package Methods.Client;

import Company.Bank;
import DB.Const;
import DB.DatabaseHandler;
import User.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Handler;

public class Client_Init {
    public static ArrayList<Client> Init(String bik){
        ResultSet resSet = null;
        ResultSet resultSet = null;
        DatabaseHandler handler = new DatabaseHandler();



        ArrayList<String> client_id = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<Client>();
        ArrayList<Client> final_clients = new ArrayList<>();

        String select = "SELECT * FROM " + Const.CLIENTS_TABLE;
        String select1 = "SELECT * FROM " + Const.CLIENT_BANK_TABLE + " WHERE " +
                Const.CLIENT_BANK_BANKID + "=?";

        try {
            PreparedStatement prSt = handler.getDbConnection().prepareStatement(select);
            PreparedStatement prSt1 = handler.getDbConnection().prepareStatement(select1);

            prSt1.setString(1, bik);

            resSet = prSt.executeQuery();
            resultSet = prSt1.executeQuery();

            while (resSet.next()) {
                String name = resSet.getString(Const.CLIENTS_NAME);
                String password = resSet.getString(Const.CLIENTS_PASSWORD);
                String passport  = resSet.getString(Const.CLIENTS_PASSPORT);
                String email = resSet.getString(Const.CLIENTS_EMAIL);
                String phone_number = resSet.getString(Const.CLIENTS_PHONENUMBER);
                String id = resSet.getString(Const.CLIENTS_ID);
                String country = resSet.getString(Const.CLIENTS_COUNTRY);
                String company = resSet.getString(Const.CLIENTS_COMPANY);
                boolean approve = resSet.getBoolean(Const.CLIENTS_APPROVE);

                clients.add(new Client(name, passport, id, phone_number, email, bik, password, country,
                        company, approve));
            }

            while (resultSet.next()) {
                client_id.add(resultSet.getString(Const.CLIENT_BANK_CLIENTID));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Client cl : clients)
            if(client_id.contains(cl.getPassport()))
                final_clients.add(cl);

        return final_clients;
    }
}
