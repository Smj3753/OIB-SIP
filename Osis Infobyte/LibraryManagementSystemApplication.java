import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    String bookId;
    String title;
    String author;
    boolean isAvailable;

    Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class Member {
    String memberId;
    String name;
    String email;

    Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }
}

class LibraryManagementSystem {
    Map<String, Book> books;
    Map<String, Member> members;

    LibraryManagementSystem() {
        books = new HashMap<>();
        members = new HashMap<>();
    }

    // Admin module functions

    void addBook(String bookId, String title, String author) {
        books.put(bookId, new Book(bookId, title, author));
        System.out.println("Book added successfully.");
    }

    void removeBook(String bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    void addMember(String memberId, String name, String email) {
        members.put(memberId, new Member(memberId, name, email));
        System.out.println("Member added successfully.");
    }

    void removeMember(String memberId) {
        if (members.containsKey(memberId)) {
            members.remove(memberId);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // User module functions

    void viewBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books.values()) {
            if (book.isAvailable) {
                System.out.println(book.bookId + ": " + book.title + " by " + book.author);
            }
        }
    }

    void issueBook(String bookId, String memberId) {
        if (books.containsKey(bookId) && members.containsKey(memberId)) {
            Book book = books.get(bookId);
            if (book.isAvailable) {
                book.isAvailable = false;
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book is not available for issue.");
            }
        } else {
            System.out.println("Book or member not found.");
        }
    }

    void returnBook(String bookId) {
        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            book.isAvailable = true;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibraryManagementSystemApplication {
    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Admin Module
        librarySystem.addBook("B001", "Introduction to Java", "John Doe");
        librarySystem.addBook("B002", "Data Structures and Algorithms", "Jane Smith");
        librarySystem.addMember("M001", "Alice", "alice@example.com");

        // User Module
        librarySystem.viewBooks();
        librarySystem.issueBook("B001", "M001");
        librarySystem.viewBooks();
        librarySystem.returnBook("B001");
        librarySystem.viewBooks();
    }
}
