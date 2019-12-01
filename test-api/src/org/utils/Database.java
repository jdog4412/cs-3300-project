package org.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String dbName = "jdbc:postgresql://cs3300.cuv9gmqa1npq.us-east-2.rds.amazonaws.com:5432/cs_3300_project";
    private final String dbUsername = "group8";
    private final String dbPassword = "GroupE!ghtFinalProject";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(dbName, dbUsername, dbPassword);
    }

    public void deleteUser(String username) {
        String SQL = "DELETE FROM public.user WHERE username=?";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String username, String password, String firstName, String lastName, String email, String card, String phoneNumber) {
        String SQL = "INSERT INTO public.user(username, password, first_name, last_name, email, card, phone_number) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, card);
            pstmt.setString(7, phoneNumber);
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

    public String getPassword(String username) {
        String SQL = "SELECT password FROM public.user WHERE username= ?";
        String password = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
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

    public String getPhoneNumber(String username) {
        String SQL = "SELECT phone_number FROM public.user WHERE username = ?";
        String address = "";

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                address = rs.getString("phone_number");
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

    public boolean checkInventory(String item) {
        String SQL = "SELECT count FROM public.inventory WHERE item = ?";
        boolean inStock = false;

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, item);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    inStock = true;
                }
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return inStock;
    }

    public List<String> getItem(String item) {
        String SQL = "SELECT item, value FROM public.inventory WHERE item = ?";
        List<String> items = new ArrayList<>();

        try (Connection conn = connect()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, item);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(rs.getString("item"));
                items.add(rs.getString("value"));
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return items;
    }
}
