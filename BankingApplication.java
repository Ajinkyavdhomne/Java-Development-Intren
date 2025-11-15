import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Transaction failed.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        }
    }

    // Check balance
    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

public class BankingApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Welcome to Simple Banking Application ===");

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = readDouble(sc);

        BankAccount account = new BankAccount(name, initialBalance);

        while (true) {
            System.out.println("\n=== Banking Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            System.out.print("Choose an option (1-4): ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = readDouble(sc);
                    account.deposit(depositAmount);
                    break;

                case "2":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = readDouble(sc);
                    account.withdraw(withdrawAmount);
                    break;

                case "3":
                    System.out.println("Account Holder: " + account.getAccountHolder());
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;

                case "4":
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1–4.");
            }
        }
    }

    // Method to safely read double values
    private static double readDouble(Scanner sc) {
        while (true) {
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}


// for compilation and execution
//javac BankingApplication.java 
//java BankingApplication

