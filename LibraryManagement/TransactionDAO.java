import java.sql.*;

public class TransactionDAO {

    public void borrowBook(int userId, int bookId) {
        BookDAO bookDAO = new BookDAO();

        if (!bookDAO.isAvailable(bookId)) {
            System.out.println("Book not available.");
            return;
        }

        String query = "INSERT INTO transactions (user_id, book_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

            bookDAO.updateAvailability(bookId, false);

            System.out.println("Book borrowed successfully!");

        } catch (Exception e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    public void returnBook(int transactionId, int bookId) {
        BookDAO bookDAO = new BookDAO();

        String query = "UPDATE transactions SET date_returned = NOW() WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transactionId);
            stmt.executeUpdate();

            bookDAO.updateAvailability(bookId, true);

            System.out.println("Book returned successfully!");

        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    public void viewTransactions(int nextInt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewTransactions'");
    }

    public void recordTransaction(int wId, String string, double wAmt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recordTransaction'");
    }
}

