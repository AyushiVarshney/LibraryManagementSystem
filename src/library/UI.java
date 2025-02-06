package library;

import library.factory.UserFactory;
import library.models.Book;
import library.models.User;

import java.util.Scanner;

public class UI {
    public static void viewLibrarianOptions() {
        System.out.println("1. Add Book");
        System.out.println("2. Update Book");
        System.out.println("3. Delete Book");
        System.out.println("10. View All Books");
        System.out.println("9. Back to Login or Register");
        System.out.print("Enter your choice: ");
    }

    public static void viewMemberOptions() {
        System.out.println("4. View Available Books");
        System.out.println("5. View Borrowed Books");
        System.out.println("6. Borrow Book");
        System.out.println("7. Return Book");
        System.out.println("8. Reserve Book");
        System.out.println("9. Back to Login or Register");
        System.out.print("Enter your choice: ");
    }

    public static Book getBookDetailsFromUser(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        return new Book(title, author, isbn, category);
    }

    public static Book getUpdatedBookDetailsFromUser(Scanner scanner) {
        System.out.print("Enter ISBN for book which you want to update: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter updated title else leave empty: ");
        String updatedTitle = scanner.nextLine();
        System.out.print("Enter updated category else leave empty: ");
        String updatedCategory = scanner.nextLine();
        System.out.print("Enter updated author else leave empty: ");
        String updatedAuthor = scanner.nextLine();
        return new Book(updatedTitle, updatedAuthor, isbn, updatedCategory);
    }

    public static String getISBNFromUser(Scanner scanner) {
        System.out.println("Enter ISBN for book which you want to remove: ");
        return scanner.nextLine();
    }

    public static String getTitleFromUser(Scanner scanner, int choice) {
        if(choice == 6)
            System.out.println("Enter title of book to borrow: ");
        else if(choice == 7)
            System.out.println("Enter title of book to return: ");
        else if(choice == 8)
            System.out.println("Enter title of book to reserve: ");
        return scanner.nextLine();
    }

    public static User getUserDetails(Scanner scanner) {
        System.out.print("Register as Member/Librarian or Login: ");
        String userType = scanner.nextLine();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Your Phone Number: ");
        String phoneNumber = scanner.nextLine();

        return UserFactory.createUser(userType, userName, phoneNumber);
    }
}
