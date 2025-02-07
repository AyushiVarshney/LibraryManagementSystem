# Library Management System

This Java-based Library Management System provides a command-line interface for managing books, members, and loans. It allows librarians to add, update, and remove books, while members can borrow, return, and reserve books. The system also includes features for sending notifications (e.g., SMS) for due dates, overdue books, and book availability.

## Features

*   **Book Management:** Add, update, and remove books from the catalog. Search for books by title or ISBN.
*   **Member Management:** Register new members and manage their information.
*   **Loan Management:** Borrow and return books. Track loan due dates and overdue books.
*   **Book Reservation:** Reserve books that are currently unavailable.
*   **Notifications:** Send SMS notifications to members for due dates, overdue books, and when reserved books become available.
*   **Command Pattern:** Uses the Command pattern for book management operations.
*   **Design Patterns:** Implements several design patterns, including Factory, Singleton, Adapter, Facade, Command, and Iterator.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or higher

### Running the Project

1.  Clone the repository:

    ```bash
    git clone [https://github.com/AyushiVarshney/LibraryManagementSystem.git](https://www.google.com/search?q=https://github.com/AyushiVarshney/LibraryManagementSystem.git)
    ```

2.  Import project in your desired IDE and simply run LibraryApp file.

## Usage

The application provides a command-line interface with a menu-driven system. Upon running the application, you will be prompted to register or login. The available options will depend on whether you log in as a member or librarian.

### Librarian Options

*   Add a book
*   Update a book
*   Remove a book
*   View all books
*   Login/Signup
*   Exit

### Member Options

*   View available books
*   View borrowed books
*   Borrow a book
*   Return a book
*   Reserve a book
*   Login/Signup
*   Exit

Follow the prompts in the application to perform the desired actions.