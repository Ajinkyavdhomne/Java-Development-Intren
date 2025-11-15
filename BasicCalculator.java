import java.math.BigDecimal;
import java.util.Scanner;

public class BasicCalculator {

    private static BigDecimal readNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return new BigDecimal(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid numeric value (e.g. 12, -3.5, 0.002).");
            }
        }
    }

    private static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    private static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    private static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    private static BigDecimal divide(BigDecimal a, BigDecimal b) {
        // Caller ensures b != 0
        return a.divide(b, 10, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Basic Calculator ===");

        while (true) {
            System.out.println("\nSelect operation:");
            System.out.println("1. Add (+)");
            System.out.println("2. Subtract (-)");
            System.out.println("3. Multiply (*)");
            System.out.println("4. Divide (/)");
            System.out.println("5. Exit");

            System.out.print("Enter choice (1-5): ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                case "+":
                    BigDecimal a1 = readNumber(sc, "Enter first number: ");
                    BigDecimal b1 = readNumber(sc, "Enter second number: ");
                    System.out.println("Result: " + add(a1, b1).toPlainString());
                    break;
                case "2":
                case "-":
                    BigDecimal a2 = readNumber(sc, "Enter first number: ");
                    BigDecimal b2 = readNumber(sc, "Enter second number: ");
                    System.out.println("Result: " + subtract(a2, b2).toPlainString());
                    break;
                case "3":
                case "*":
                    BigDecimal a3 = readNumber(sc, "Enter first number: ");
                    BigDecimal b3 = readNumber(sc, "Enter second number: ");
                    System.out.println("Result: " + multiply(a3, b3).toPlainString());
                    break;
                case "4":
                case "/":
                    BigDecimal a4 = readNumber(sc, "Enter dividend (first number): ");
                    BigDecimal b4 = readNumber(sc, "Enter divisor (second number): ");
                    if (b4.compareTo(BigDecimal.ZERO) == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                    } else {
                        System.out.println("Result: " + divide(a4, b4).toPlainString());
                    }
                    break;
                case "5":
                case "exit":
                    System.out.println("Exiting calculator. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose a number between 1 and 5.");
            }
        }
    }
}

// for compilation and execution
//javac BasicCalculator.java
//java BasicCalculator