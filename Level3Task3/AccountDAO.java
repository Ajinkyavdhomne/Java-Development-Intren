import java.sql.*;

public class AccountDAO {

    public void addAccount(String name, double balance) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO accounts(name, balance) VALUES(?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setDouble(2, balance);
            ps.executeUpdate();
            System.out.println("Account added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    public void viewAccounts() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM accounts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Account List ");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Balance: " + rs.getDouble("balance"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching accounts: " + e.getMessage());
        }
    }

    public void updateAccountName(int id, String newName) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE accounts SET name = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Account updated successfully!");

        } catch (Exception e) {
            System.out.println("Error updating account: " + e.getMessage());
        }
    }

    public void deleteAccount(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM accounts WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Account deleted successfully!");

        } catch (Exception e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    public void updateBalance(int id, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }
}
