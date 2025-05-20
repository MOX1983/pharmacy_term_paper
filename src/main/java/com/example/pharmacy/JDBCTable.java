package com.example.pharmacy;

import java.sql.*;

public class JDBCTable extends Config{

    public static void writesql(User user){
        String insert = "INSERT INTO " + TablDB.NAME_TABLE + "(" +
                TablDB.FIRST_NAME_USERS + "," + TablDB.LOGIN_USERS + ","+ TablDB.PASS_USERS + ")" +
                " VALUES" + "(?,?,?)";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement ps = con.prepareStatement(insert);

            ps.setString(1, user.getfirstName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet readUserSQL(User user){
        ResultSet rs = null;

        String select = "SELECT * FROM " + TablDB.NAME_TABLE +
                " WHERE login =? AND pass =?";
        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement prst = con.prepareStatement(select);
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getPassword());
            rs = prst.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static ResultSet readPillsSQL(){
        ResultSet rs = null;

        String select = "select namepills, quantity, expiratiodate from  " + TablDB.NAME_TABLE_PILLS;
        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(select);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static ResultSet readAllPillsSQL(Pills pill){
        ResultSet rs = null;

        String select = "SELECT * FROM " + TablDB.NAME_TABLE_PILLS +
                " WHERE namepills = ?";
        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, pill.getName());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public static void insertPills(Pills pill ){
        String insert = "INSERT INTO pills(iduser, namepills, description, quantity, expiratiodate, image) " +
                " VALUES" + "(?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement ps = con.prepareStatement(insert);

            ps.setInt(1, pill.getIdUser());
            ps.setString(2, pill.getName());
            ps.setString(3, pill.getDescription());
            ps.setInt(4, pill.getQuantity());
            ps.setDate(5, pill.getExpiratioDate());
            ps.setBytes(6, pill.getImg());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delePills(Pills pill){
        String delete = "DELETE FROM pills WHERE namepills = ?";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement ps = con.prepareStatement(delete);

            ps.setString(1, pill.getName());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isLoginTaken(String login) {
        String query = "SELECT COUNT(*) FROM users WHERE login = ?";

        try(Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement prst = con.prepareStatement(query);

            prst.setString(1, login);
            ResultSet res = prst.executeQuery();

            if(res.next()){
                return res.getInt(1) > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
}
