import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListBooks {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "aswin@123")) {

            String sql = "SELECT * FROM books";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                int total = rs.getInt("total_quantity");
                int available = rs.getInt("available_quantity");

                System.out.println(id + " | " + title + " | " + author + " | " + year +
                        " | Total: " + total + " | Available: " + available);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

