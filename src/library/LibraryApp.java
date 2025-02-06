package library;

import library.adapter.SMSAdapter;
import library.command.BookManagementSystem;
import library.facade.LibraryFacade;
import library.models.Book;
import library.models.User;
import library.adapter.ExternalSMSService;
import library.adapter.NotificationService;
import library.service.BookService;
import library.service.LoanService;
import library.service.UserSMSService;
import library.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryApp {
    private static final ExternalSMSService smsService = new UserSMSService();
    private static final NotificationService notificationService = new SMSAdapter(smsService);
    private static final LoanService loanService = new LoanService();
    private static final BookService bookService = new BookService();
    private static final UserService userService = new UserService();
    private static final BookManagementSystem bookManagementSystem = new BookManagementSystem();
    private static final LibraryFacade libraryFacade = new LibraryFacade(loanService, bookService, bookManagementSystem, userService, notificationService);
    private static final DailyTaskScheduler scheduler = new DailyTaskScheduler(loanService);
    private static final Scanner scanner = new Scanner(System.in);
    private static User user = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System");
        user = loginOrSignup();
        scheduler.scheduleDueNotifications();
        if(!userService.getUsers().isEmpty()) {
            while (true) {
                if(user != null && user.getRole().equalsIgnoreCase("librarian")) {
                    UI.viewLibrarianOptions();
                } else if(user != null && user.getRole().equalsIgnoreCase("member")){
                    UI.viewMemberOptions();
                }
                try {
                    if(user != null)
                        performUserOperations(user.getName());
                } catch (InputMismatchException e) {
                    System.out.println("Please enter valid choice");
                }
            }
        }
    }

    private static void performUserOperations(String userName) {
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: {
                Book book = UI.getBookDetailsFromUser(scanner);
                libraryFacade.addBook(book);
                break;
            }
            case 2: {
                Book updatedBook = UI.getUpdatedBookDetailsFromUser(scanner);
                libraryFacade.updateBook(updatedBook);
                break;
            }
            case 3: {
                String isbn = UI.getISBNFromUser(scanner);
                libraryFacade.removeBook(isbn);
                break;
            }
            case 4: {
                libraryFacade.viewAvailableBooks();
                break;
            }
            case 5: {
                libraryFacade.viewBorrowedBooks();
                break;
            }
            case 6: {
                String title = UI.getTitleFromUser(scanner, choice);
                libraryFacade.borrowBook(userName, title);
                break;
            }
            case 7: {
                String title = UI.getTitleFromUser(scanner, choice);
                libraryFacade.returnBook(userName, title);
                break;
            }
            case 8: {
                String title = UI.getTitleFromUser(scanner, choice);
                libraryFacade.reserveBook(userName, title);
                break;
            }
            case 9: {
                user = loginOrSignup();
                break;
            }
            case 10: {
                libraryFacade.viewAllBooks();
                break;
            }
            default: {
                System.out.println("Exiting....");
                scanner.close();
                System.exit(0);
            }
        }
    }

    private static User loginOrSignup() {
        try {
            User user = UI.getUserDetails(scanner);
            userService.addUser(user);
            return user;
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter valid role ");
            return null;
        }
    }
}