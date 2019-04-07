package repository;

import database.ConnectionDatabase;
import database.OperatorDatabase;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyUserRepository implements UserRepository {

    private static List<User> db = new ArrayList<User>();

    public User getUserByName (String uName) {
        User usr  = null;

        Connection con = ConnectionDatabase.connect();
        OperatorDatabase operatorDatabase = new OperatorDatabase();
        operatorDatabase.createTable();

        try{
            PreparedStatement preparedStmt = con.prepareStatement("SELECT Username, E_mail, Premium, Administrator FROM users WHERE Username = ?");
            preparedStmt.setString(1, uName);
            ResultSet res = preparedStmt.executeQuery();

            while (res.next()) {
                usr = new User();
                usr.setuName(res.getString("Username"));
                usr.setEmail(res.getString("E_mail"));
                usr.setIsPremium(res.getBoolean("Premium"));
                usr.setIsAdmin(res.getBoolean("Administrator"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usr;
    }

    public Boolean checkPass (String uName, String pass) {
        Boolean correctPass = false;

        Connection con = ConnectionDatabase.connect();

        try {
            PreparedStatement preparedStmt = con.prepareStatement("SELECT Username, E_mail, Premium, Administrator FROM users WHERE Username = ? AND Password = ?");
            preparedStmt.setString(1, uName);
            preparedStmt.setString(2, pass);

            ResultSet res = preparedStmt.executeQuery();

            while(res.next()) {
                correctPass = true;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correctPass;
    }

    public List<User> getAllUserData() {
        List<User> userList = new ArrayList<User>();

        Connection con = ConnectionDatabase.connect();

        try {
            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery("SELECT Username, E_mail, Premium FROM users");

            while (res.next()) {
                User usr = new User();
                usr.setuName(res.getString("Username"));
                usr.setEmail(res.getString("E_mail"));
                usr.setIsPremium(res.getBoolean("Premium"));
                userList.add(usr);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void add(User user) {
        try{
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");

            PreparedStatement preparedStmt = con.prepareStatement("INSERT INTO users (Username, E_mail, Password, Premium, Administrator) VALUES (?, ?, ?, ?, ?)");
            preparedStmt.setString(1, user.getuName());
            preparedStmt.setString(2, user.getEmail());
            preparedStmt.setString(3, user.getPassword());
            preparedStmt.setBoolean(4, user.getIsPremium());
            preparedStmt.setBoolean(5, user.getIsAdmin());

            preparedStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePremiumStatus (String[] uNameList, String[] checkedList){

        Connection con = ConnectionDatabase.connect();

        for (String uName : uNameList) {
            try {
                PreparedStatement preparedStmt = con.prepareStatement("UPDATE users SET Premium = ? WHERE Username = ?");
                preparedStmt.setBoolean(1, Arrays.asList(checkedList).contains(uName));
                preparedStmt.setString(2, uName);
                preparedStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}