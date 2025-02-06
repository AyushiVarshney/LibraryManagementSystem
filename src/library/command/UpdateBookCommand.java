package library.command;

import library.models.Book;
import library.service.BookService;

public class UpdateBookCommand implements Command {
    private Book book;
    private BookService bookService;

    public UpdateBookCommand(Book book, BookService bookService){
        this.book = book;
        this.bookService = bookService;
    }

    @Override
    public void execute(){
        this.book = bookService.updateBook(book);
        if(book == null) System.out.println("Book not found!");
        else System.out.println("Updated book - ISBN: " + book.getIsbn() + " Title: " + book.getTitle() + " Author: " + book.getAuthor() + " Category: " + book.getCategory());
    }
}
