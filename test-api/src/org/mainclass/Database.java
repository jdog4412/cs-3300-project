package org.mainclass;

import java.sql.*;

public class Database {
    private final String dbName = "jdbc:postgresql://localhost:5432/cs_3300_project";
    private final String dbUsername = "postgres";
    private final String dbPassword = "password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(dbName, dbUsername, dbPassword);
    }

    public String getUsernameAndPassword() {
        String SQL = "SELECT username, password FROM public.user";
        String user = "";
        String username = "";
        String password = "";



        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        user = username + password;
        return user;
    }

    //    public String getPassword() {
//
//    }
//
    public void setUserNameAndPassword(String username, String password) {
        String SQL = "INSERT INTO public.user(username, password) "
                + "VALUES(?, ?)";

        try (Connection conn = connect()) {
            PreparedStatement psmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, username);
            psmt.setString(2, password);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }

    public void deleteUserNameAndPassword(String username, String password) {
        String SQL = "DELETE FROM public.user WHERE username = ? AND password = ?";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    public boolean checkUsernameAndPassword(String username, String password) {
        String SQL = "SELECT username, password FROM public.user where password = ?";
        boolean check = false;

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, password);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("username").equals(username)) {
                    check = true;
                }
            }

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return check;
    }
}
