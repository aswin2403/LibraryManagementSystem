import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static void main(String[] args) {
        try {
            // Change username/password if different
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root";
            String password = "aswin@123";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to Database Successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Connection Failed!");
            e.printStackTrace();
        }
    }
}
