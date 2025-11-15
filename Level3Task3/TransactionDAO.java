import java.sql.*;

public class TransactionDAO {

    public void recordTransaction(int accountId, String type, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO transactions(account_id, type, amount) VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error recording transaction: " + e.getMessage());
        }
    }

    public void viewTransactions(int accountId) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM transactions WHERE account_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            System.out.println("Transaction History ");
            while (rs.next()) {
                System.out.println("Type: " + rs.getString("type") +
                                   ", Amount: " + rs.getDouble("amount") +
                                   ", Date: " + rs.getTimestamp("date"));
            }

        } catch (Exception e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
