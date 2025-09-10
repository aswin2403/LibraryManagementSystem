import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class ReturnBook {
    public static void main(String[] args) {
        int loanId = 1;  // loan record ID (from loans table)
        LocalDate today = LocalDate.now();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                "aswin@123")) {

            conn.setAutoCommit(false); // start transaction

            // 1. Update loan record
            String updateLoan = "UPDATE loans SET status='RETURNED', return_date=? WHERE id=?";
            PreparedStatement loanStmt = conn.prepareStatement(updateLoan);
            loanStmt.setDate(1, java.sql.Date.valueOf(today));
            loanStmt.setInt(2, loanId);
            int rows = loanStmt.executeUpdate();

            if (rows > 0) {
                // 2. Increase book quantity
                String updateBook = "UPDATE books b " +
                        "JOIN loans l ON b.id = l.book_id " +
                        "SET b.available_quantity = b.available_quantity + 1 " +
                        "WHERE l.id = ?";
                PreparedStatement bookStmt = conn.prepareStatement(updateBook);
                bookStmt.setInt(1, loanId);
                bookStmt.executeUpdate();

                conn.commit();
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("❌ Loan ID not found!");
                conn.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

