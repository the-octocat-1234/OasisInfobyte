import java.util.*;
public class Book {
    private int bookId;
    private String title;
    private String author;
    private int availableCopies;

    public Book(int bookId, String title, String author, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available Copies: " + availableCopies;
    }
}
class User {
    private int userId;
    private String name;
    private String email;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Email: " + email;
    }
}

class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<String> transactions = new ArrayList<>();

    public LibraryService() {
        // Sample data
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 5));
        books.add(new Book(2, "1984", "George Orwell", 4));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", 3));

        users.add(new User(1, "Alice", "alice@example.com"));
        users.add(new User(2, "Bob", "bob@example.com"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public boolean borrowBook(int userId, int bookId) {
        Book book = getBookById(bookId);
        User user = getUserById(userId);
        if (book != null && user != null && book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            transactions.add("User " + user.getName() + " borrowed " + book.getTitle());
            return true;
        }
        return false;
    }

    public boolean returnBook(int userId, int bookId) {
        Book book = getBookById(bookId);
        User user = getUserById(userId);
        if (book != null && user != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            transactions.add("User " + user.getName() + " returned " + book.getTitle());
            return true;
        }
        return false;
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public List<String> getAllTransactions() {
        return transactions;
    }
}

class LibraryManagementSystem {
    private static LibraryService libraryService = new LibraryService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View all books");
            System.out.println("2. View all users");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. View transactions");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    viewAllUsers();
                    break;
                case 3:
                    borrowBook(scanner);
                    break;
                case 4:
                    returnBook(scanner);
                    break;
                case 5:
                    viewTransactions();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAllBooks() {
        for (Book book : libraryService.getAllBooks()) {
            System.out.println(book);
        }
    }

    private static void viewAllUsers() {
        for (User user : libraryService.getAllUsers()) {
            System.out.println(user);
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        if (libraryService.borrowBook(userId, bookId)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Failed to borrow book.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        if (libraryService.returnBook(userId, bookId)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return book.");
        }
    }

    private static void viewTransactions() {
        for (String transaction : libraryService.getAllTransactions()) {
            System.out.println(transaction);
        }
    }
}


