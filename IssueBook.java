import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class IssueBook {
    public static void main(String[] args) {
        int bookId = 1;    // which book to issue
        int memberId = 1;  // which member is borrowing
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(14); // 2 weeks due

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "aswin@123")) {

            conn.setAutoCommit(false); // start transaction

            // 1. Check if book is available
            String checkSql = "SELECT available_quantity FROM books WHERE id = ? FOR UPDATE";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("available_quantity") > 0) {
                // 2. Insert loan record
                String loanSql = "INSERT INTO loans (book_id, member_id, issue_date, due_date, status) VALUES (?, ?, ?, ?, 'ISSUED')";
                PreparedStatement loanStmt = conn.prepareStatement(loanSql);
                loanStmt.setInt(1, bookId);
                loanStmt.setInt(2, memberId);
                loanStmt.setDate(3, java.sql.Date.valueOf(today));
                loanStmt.setDate(4, java.sql.Date.valueOf(dueDate));
                loanStmt.executeUpdate();

                // 3. Update book quantity
                String updateSql = "UPDATE books SET available_quantity = available_quantity - 1 WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                conn.commit();
                System.out.println("✅ Book issued successfully!");
            } else {
                System.out.println("❌ Book not available!");
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

