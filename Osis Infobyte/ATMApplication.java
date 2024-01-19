import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User class to store user information
class User {
    String userId;
    String userPin;
    double balance;
    List<String> transactionsHistory;

    User(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionsHistory = new ArrayList<>();
    }

    // Other user-related methods
}

// ATM class to handle ATM functionalities
class ATM {
    List<User> users;
    User currentUser;

    ATM() {
        users = new ArrayList<>();
        // Initialize some demo users
        users.add(new User("user1", "1234", 1000.0));
        users.add(new User("user2", "5678", 500.0));
    }

    // Main method to start the ATM application
    void start() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for user id and pin
        System.out.print("Enter User ID: ");
        String userIdInput = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pinInput = scanner.nextLine();

        // Authenticate user
        currentUser = authenticateUser(userIdInput, pinInput);

        if (currentUser != null) {
            // Unlock ATM functionalities
            showMenu(scanner);
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    // Authenticate user based on user id and pin
    User authenticateUser(String userId, String pin) {
        for (User user : users) {
            if (user.userId.equals(userId) && user.userPin.equals(pin)) {
                return user;
            }
        }
        return null; // Authentication failed
    }

    // Display the main menu for the user
    void showMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            // Read user's choice
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Perform the selected operation
            switch (choice) {
                case 1:
                    displayTransactionsHistory();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    // Display user's transaction history
    void displayTransactionsHistory() {
        System.out.println("\nTransactions History:");
        for (String transaction : currentUser.transactionsHistory) {
            System.out.println(transaction);
        }
    }

    // Withdraw money from the user's account
    void withdraw(Scanner scanner) {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentUser.balance) {
            currentUser.balance -= amount;
            currentUser.transactionsHistory.add("Withdrawal: -$" + amount);
            System.out.println("Withdrawal successful. Current balance: $" + currentUser.balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    // Deposit money into the user's account
    void deposit(Scanner scanner) {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentUser.balance += amount;
            currentUser.transactionsHistory.add("Deposit: +$" + amount);
            System.out.println("Deposit successful. Current balance: $" + currentUser.balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Transfer money from the user's account to another user's account
    void transfer(Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter transfer amount: $");
        double amount = scanner.nextDouble();

        // Find the recipient user
        User recipient = null;
        for (User user : users) {
            if (user.userId.equals(recipientId)) {
                recipient = user;
                break;
            }
        }

        if (recipient != null && amount > 0 && amount <= currentUser.balance) {
            currentUser.balance -= amount;
            recipient.balance += amount;

            currentUser.transactionsHistory.add("Transfer to " + recipientId + ": -$" + amount);
            recipient.transactionsHistory.add("Transfer from " + currentUser.userId + ": +$" + amount);

            System.out.println("Transfer successful. Current balance: $" + currentUser.balance);
        } else {
            System.out.println("Invalid recipient, amount, or insufficient funds.");
        }
    }
}

// Main class to run the ATM application
public class ATMApplication {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
