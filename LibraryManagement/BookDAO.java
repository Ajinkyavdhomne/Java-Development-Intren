import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(String title, String author) {
        String query = "INSERT INTO books (title, author) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<String> getAllBooks() {
        List<String> list = new ArrayList<>();

        String query = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add("ID: " + rs.getInt("id")
                        + ", Title: " + rs.getString("title")
                        + ", Author: " + rs.getString("author")
                        + ", Available: " + rs.getBoolean("available"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }

        return list;
    }

    public boolean isAvailable(int bookId) {
        String query = "SELECT available FROM books WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("available");
            }

        } catch (Exception e) {
            System.out.println("Error checking availability: " + e.getMessage());
        }

        return false;
    }

    public void updateAvailability(int bookId, boolean available) {
        String query = "UPDATE books SET available = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, available);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updating availability: " + e.getMessage());
        }
    }
}
