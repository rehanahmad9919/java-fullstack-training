import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private final String FILE_NAME = "books.dat";

    public Library() {
        loadData();
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
        saveData();
        System.out.println("Book added successfully!");
    }

    // View all books
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(System.out::println);
    }

    // Search book
    public Book search(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    // Issue book
    public void issueBook(int id) {
        Book b = search(id);
        if (b == null) {
            System.out.println("Book not found!");
        } else if (b.isIssued()) {
            System.out.println("Book already issued!");
        } else {
            b.issue();
            saveData();
            System.out.println("Book issued successfully!");
        }
    }

    // Return book
    public void returnBook(int id) {
        Book b = search(id);
        if (b == null) {
            System.out.println("Book not found!");
        } else if (!b.isIssued()) {
            System.out.println("Book was not issued!");
        } else {
            b.returnBook();
            saveData();
            System.out.println("Book returned successfully!");
        }
    }

    // Save books to file
    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(books);
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    // Load books from file
    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) in.readObject();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
    }
}
