
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " +
                (issued ? "Issued" : "Available"));
        System.out.println("----------------------");
    }
}

public class LibraryManagementSystem {
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== LIBRARY MANAGEMENT ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBook();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 0 -> System.out.println("Thank you!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void addBook() {
        System.out.print("Enter book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findBook(id) != null) {
            System.out.println("Book ID already exists!");
            return;
        }

        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            book.display();
        }
    }

    static void searchBook() {
        System.out.print("Enter book ID: ");
        int id = sc.nextInt();

        Book book = findBook(id);

        if (book != null) {
            book.display();
        } else {
            System.out.println("Book not found!");
        }
    }

    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = sc.nextInt();

        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found!");
        } else if (book.issued) {
            System.out.println("Book is already issued!");
        } else {
            book.issued = true;
            System.out.println("Book issued successfully!");
        }
    }

    static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = sc.nextInt();

        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found!");
        } else if (!book.issued) {
            System.out.println("Book was not issued!");
        } else {
            book.issued = false;
            System.out.println("Book returned successfully!");
        }
    }

    static Book findBook(int id) {
        for (Book book : books) {
            if (book.id == id) {
                return book;
            }
        }
        return null;
    }
}
