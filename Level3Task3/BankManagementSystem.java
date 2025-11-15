import java.util.Scanner;

public class BankManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Add Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Update Account Name");
            System.out.println("4. Delete Account");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();
                    accountDAO.addAccount(name, bal);
                    break;

                case 2:
                    accountDAO.viewAccounts();
                    break;

                case 3:
                    System.out.print("Enter account ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    accountDAO.updateAccountName(id, newName);
                    break;

                case 4:
                    System.out.print("Enter account ID: ");
                    accountDAO.deleteAccount(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter account ID: ");
                    int depId = sc.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depAmt = sc.nextDouble();
                    accountDAO.updateBalance(depId, depAmt);
                    transactionDAO.recordTransaction(depId, "Deposit", depAmt);
                    System.out.println("Deposit successful!");
                    break;

                case 6:
                    System.out.print("Enter account ID: ");
                    int wId = sc.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double wAmt = sc.nextDouble();
                    accountDAO.updateBalance(wId, -wAmt);
                    transactionDAO.recordTransaction(wId, "Withdraw", wAmt);
                    System.out.println("Withdrawal successful!");
                    break;

                case 7:
                    System.out.print("Enter account ID: ");
                    transactionDAO.viewTransactions(sc.nextInt());
                    break;

                case 8:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
