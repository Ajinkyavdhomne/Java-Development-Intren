import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(String name) {
        String query = "INSERT INTO users (name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("User added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }

        return users;
    }
}

