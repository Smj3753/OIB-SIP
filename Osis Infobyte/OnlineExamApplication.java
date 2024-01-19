import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    String username;
    String password;
    String profile;

    User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    // Other user-related methods
}

class OnlineExamSystem {
    Map<String, User> users;
    Timer timer;
    int remainingTime;

    OnlineExamSystem() {
        users = new HashMap<>();
        // Initialize some demo users
        users.put("student1", new User("student1", "pass123", "Student"));
        users.put("professor1", new User("professor1", "pass456", "Professor"));
        // Set a default timer duration (in seconds)
        remainingTime = 300;
    }

    void start() {
        Scanner scanner = new Scanner(System.in);

        // User login
        User currentUser = login(scanner);

        if (currentUser != null) {
            // Allow user to update profile and password
            updateProfileAndPassword(currentUser, scanner);

            // Start the exam
            startExam(scanner);
        } else {
            System.out.println("Login failed. Exiting.");
        }
    }

    User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user != null && user.password.equals(password)) {
            System.out.println("Login successful. Welcome, " + user.profile + " " + user.username + "!");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    void updateProfileAndPassword(User user, Scanner scanner) {
        System.out.print("Do you want to update your profile? (yes/no): ");
        String updateProfileChoice = scanner.nextLine();

        if (updateProfileChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter new profile information: ");
            String newProfile = scanner.nextLine();
            user.profile = newProfile;
            System.out.println("Profile updated successfully.");
        }

        System.out.print("Do you want to update your password? (yes/no): ");
        String updatePasswordChoice = scanner.nextLine();

        if (updatePasswordChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            user.password = newPassword;
            System.out.println("Password updated successfully.");
        }
    }

    void startExam(Scanner scanner) {
        System.out.println("Exam started!");

        // Schedule the timer for auto-submit
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                autoSubmit();
            }
        }, remainingTime * 1000);

        // Implement the exam questions and answer selection here
        // For simplicity, let's assume the student has 5 minutes (300 seconds) to complete the exam.

        System.out.println("Select your answers for the MCQs...");

        // Keep the session open until the timer expires or the student manually submits
        while (remainingTime > 0) {
            // Implement logic for answering MCQs

            // Simulate time passing
            try {
                Thread.sleep(1000);
                remainingTime--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Display remaining time
            System.out.println("Remaining time: " + remainingTime + " seconds");

            // Check if the student wants to manually submit
            System.out.print("Do you want to submit the exam now? (yes/no): ");
            String submitChoice = scanner.nextLine();

            if (submitChoice.equalsIgnoreCase("yes")) {
                manualSubmit();
                break;
            }
        }

        // Close the exam session
        closeSession();
    }

    void autoSubmit() {
        System.out.println("Auto-submitting the exam...");
        // Implement logic for auto-submit here
        // This method is called when the timer expires
        closeSession();
    }

    void manualSubmit() {
        System.out.println("Manually submitting the exam...");
        // Implement logic for manual-submit here
        // This method is called when the user chooses to submit manually
        closeSession();
    }

    void closeSession() {
        System.out.println("Closing the exam session...");
        // Implement logic for closing the session here
        // This method is called when the exam session is closed, either by auto-submit or manual-submit
        // Perform any necessary cleanup or processing related to the exam
        timer.cancel(); // Cancel the timer
    }
}

public class OnlineExamApplication {
    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();
        examSystem.start();
    }
}
