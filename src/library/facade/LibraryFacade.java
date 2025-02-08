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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (!book.isAvailable()) {
                    System.out.println("Title: " + book.getTitle()
                            + ", Author: " + book.getAuthor()
                            + ", ISBN: " + book.getIsbn()
                            + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
                }
            }
        } else {
            System.out.println("There are no books available");
        }
    }

    public void viewAvailableBooks() {
        List<Book> books = this.getBooks();
        BookIterator iterator = new BookIterator(books);
        if(iterator.hasNext()) {
            System.out.println("Below are the available books :");
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.isAvailable()) {
                    System.out.println("Title: " + book.getTitle()
                            + ", Author: " + book.getAuthor()
                            + ", ISBN: " + book.getIsbn()
                            + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
                }
            }
        } else {
            System.out.println("There are no books available");
        }
    }

    public void viewAllBooks() {
        List<Book> books = this.getBooks();
        BookIterator iterator = new BookIterator(books);
        if(iterator.hasNext()) {
            System.out.println("Below is list of all books :");
            while (iterator.hasNext()) {
                Book book = iterator.next();
                System.out.println("Title: " + book.getTitle()
                        + ", Author: " + book.getAuthor()
                        + ", ISBN: " + book.getIsbn()
                        + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
            }
        } else {
            System.out.println("There are no books available");
        }
    }

    public void borrowBook(User user, String title){
        if(Objects.nonNull(user)) {
            Book book = bookService.findBookByTitle(title);
            loanService.borrowBook((MemberUser) user, book);
        }
    }

    public void returnBook(User user, String title){
        if(Objects.nonNull(user)) {
            Book book = bookService.findBookByTitle(title);
            loanService.returnBook((MemberUser) user, book);
        }
    }

    public void reserveBook(User user, String title) {
        Book book = bookService.findBookByTitle(title);
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
