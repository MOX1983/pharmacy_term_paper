package com.example.pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // написать чтение таблицы sql
}
