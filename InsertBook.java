import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Publisher: ");
        String publisher = sc.nextLine();

        System.out.print("Enter Year: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Total Quantity: ");
        int totalQty = Integer.parseInt(sc.nextLine());

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "aswin@123")) {

            String sql = "INSERT INTO books (title, author, publisher, year, total_quantity, available_quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, year);
            ps.setInt(5, totalQty);
            ps.setInt(6, totalQty); // available = total initially

            int rows = ps.executeUpdate();
            System.out.println(rows + " book inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
