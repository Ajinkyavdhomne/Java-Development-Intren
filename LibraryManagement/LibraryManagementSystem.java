import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    bookDAO.addBook(title, author);
                    break;

                case "2":
                    bookDAO.getAllBooks().forEach(System.out::println);
                    break;

                case "3":
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();
                    userDAO.addUser(name);
                    break;

                case "4":
                    userDAO.getAllUsers().forEach(System.out::println);
                    break;

                case "5":
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter book ID: ");
                    int bookId = Integer.parseInt(sc.nextLine());
                    transactionDAO.borrowBook(userId, bookId);
                    break;

                case "6":
                    System.out.print("Enter transaction ID: ");
                    int tId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter book ID: ");
                    int bId = Integer.parseInt(sc.nextLine());
                    transactionDAO.returnBook(tId, bId);
                    break;

                case "7":
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
