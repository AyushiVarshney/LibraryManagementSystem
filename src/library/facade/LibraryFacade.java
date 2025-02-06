package library.facade;

import library.DailyTaskScheduler;
import library.adapter.NotificationService;
import library.command.*;
import library.decorator.Reservable;
import library.decorator.ReservableBookDecorator;
import library.iterator.BookIterator;
import library.models.Book;
import library.models.Loan;
import library.models.MemberUser;
import library.models.User;
import library.service.BookService;
import library.service.LoanService;
import library.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LibraryFacade {
    private LoanService loanService;
    private BookService bookService;
    private BookManagementSystem bookManagementSystem;
    private UserService userService;
    private NotificationService notificationService;

    public LibraryFacade(LoanService loanService, BookService bookService, BookManagementSystem bookManagementSystem, UserService userService, NotificationService notificationService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.bookManagementSystem = bookManagementSystem;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void addBook(Book book) {
        Command command = new AddBookCommand(book, bookService);
        bookManagementSystem.executeCommand(command);
    }

    public void updateBook(Book updatedBook) {
        Command command = new UpdateBookCommand(updatedBook, bookService);
        bookManagementSystem.executeCommand(command);
    }

    public void removeBook(String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        Command command = new DeleteBookCommand(book, bookService);
        bookManagementSystem.executeCommand(command);
    }

    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    public void viewBorrowedBooks() {
        List<Book> books = this.getBooks();
        BookIterator iterator = new BookIterator(books);
        if(iterator.hasNext()) {
            System.out.println("Below are the borrowed books :");
        }
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (!book.isAvailable()) {
                System.out.println("Title: " + book.getTitle()
                        + ", Author: " + book.getAuthor()
                        + ", ISBN: " + book.getIsbn()
                        + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
            }
        }
    }

    public void viewAvailableBooks() {
        List<Book> books = this.getBooks();
        BookIterator iterator = new BookIterator(books);
        if(iterator.hasNext()) {
            System.out.println("Below are the available books :");
        }
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle()
                        + ", Author: " + book.getAuthor()
                        + ", ISBN: " + book.getIsbn()
                        + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
            }
        }
    }

    public void viewAllBooks() {
        List<Book> books = this.getBooks();
        BookIterator iterator = new BookIterator(books);
        if(iterator.hasNext()) {
            System.out.println("Below is list of all the available books :");
        }
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Title: " + book.getTitle()
                    + ", Author: " + book.getAuthor()
                    + ", ISBN: " + book.getIsbn()
                    + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public void borrowBook(String userName, String title){
        Book book = bookService.findBookByTitle(title);
        User user = userService.findUserByName(userName);
        if(book != null && user != null){
            loanService.borrowBook((MemberUser) user, book, LocalDate.now());
        }
    }

    public void returnBook(String userName, String title){
        Book book = bookService.findBookByTitle(title);
        User user = userService.findUserByName(userName);
        if(book != null && user != null){
            loanService.returnBook((MemberUser) user, book);
        }
    }

    public void reserveBook(String userName, String title) {
        Book book = bookService.findBookByTitle(title);
        User user = userService.findUserByName(userName);
        if(book != null && user != null){
            Reservable res = new ReservableBookDecorator(book);
            res.reserve((MemberUser) user);
            List<Loan> activeLoans = loanService.getActiveLoans();
            if(!activeLoans.isEmpty()) {
                Optional<Loan> loan = activeLoans.stream().filter(l -> l.getBook().getTitle().equalsIgnoreCase(book.getTitle())).findFirst();
                this.notificationService.notifyMemberForBookAvailability(user, book, loan.get().getDueDate());
            }
        }
    }
}
