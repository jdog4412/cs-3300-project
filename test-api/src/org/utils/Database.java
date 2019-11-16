package org.utils;

import java.sql.*;

public class Database {
    private final String dbName = "jdbc:postgresql://localhost:5432/cs_3300_project";
    private final String dbUsername = "postgres";
    private final String dbPassword = "password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(dbName, dbUsername, dbPassword);
    }

    public void setUser(String username, String password, String firstName, String lastName, String email, String address, String card) {
        String SQL = "INSERT INTO public.user(username, password, first_name, last_name, email, address, card) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, address);
            pstmt.setString(7, card);
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

    public String getFirstName(String username) {
        String SQL = "SELECT first_name FROM public.user WHERE username= ?";
        String firstName = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                firstName = rs.getString("first_name");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return firstName;
    }

    public String getLastName(String username) {
        String SQL = "SELECT last_name FROM public.user WHERE username = ?";
        String lastName = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lastName = rs.getString("last_name");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lastName;
    }

    public String getEmail(String username) {
        String SQL = "SELECT email FROM public.user WHERE username = ?";
        String email = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return email;
    }

    public String getAddress(String username) {
        String SQL = "SELECT address FROM public.user WHERE username = ?";
        String address = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                address = rs.getString("address");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return address;
    }

    public String getCard(String username) {
        String SQL = "SELECT card FROM public.user WHERE username = ?";
        String card = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                card = rs.getString("card");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return card;
    }
}
