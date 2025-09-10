import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddMember {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "aswin@123")) {

            String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "Aswin R");
            ps.setString(2, "aswin@example.com");
            ps.setString(3, "9876543210");

            int rows = ps.executeUpdate();
            System.out.println(rows + " member inserted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

