import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cs_3300_project", "postgres", "password")) {
            System.out.println("Java JDBC Example");

            System.out.println("Connected to PostgreSQL Database!");
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
