package library.command;

import library.service.BookService;
import library.singelton.LibraryCatalogue;
import library.models.Book;

public class AddBookCommand implements Command {
    private Book book;
    private BookService bookService;

    public AddBookCommand(Book book, BookService bookService){
        this.book = book;
        this.bookService = bookService;
    }

    @Override
    public void execute(){
        bookService.addBook(book);
        System.out.println("Added book: " + book.getTitle());
    }
}
