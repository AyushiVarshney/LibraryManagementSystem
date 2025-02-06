package library.command;

import library.models.Book;
import library.service.BookService;

public class DeleteBookCommand implements Command {
    private Book book;
    private BookService bookService;

    public DeleteBookCommand(Book book, BookService bookService){
        this.book = book;
        this.bookService = bookService;
    }

    @Override
    public void execute() {
        if(book == null) {
            System.out.println("No such book found in system");
        } else {
            bookService.removeBook(book);
            System.out.println("Deleted book: " + book.getTitle());
        }
    }
}
