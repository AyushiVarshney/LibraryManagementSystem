package library.service;

import library.models.Book;
import library.singelton.LibraryCatalogue;

import java.util.List;

public class BookService {
    private final LibraryCatalogue catalogue = LibraryCatalogue.getLibraryCatalogueInstance();

    public void addBook(Book book) {
        catalogue.addBook(book);
    }

    public Book updateBook(Book updatedBook) {
        for (Book book: catalogue.getBooks()) {
            if (book.getIsbn().equals(updatedBook.getIsbn())) {
                book.setTitle(updatedBook.getTitle().isBlank() ? book.getTitle() : updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor().isBlank() ? book.getAuthor() : updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory().isBlank() ? book.getCategory() : updatedBook.getCategory());
                return book;
            }
        }
        return null;
    }

    public void removeBook(Book book) {
        catalogue.getBooks().remove(book);
    }

    public List<Book> getAllBooks() {
        return catalogue.getBooks();
    }

    public Book findBookByIsbn(String isbn) {
        return this.getAllBooks()
                .stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    public Book findBookByTitle(String title) {
        return this.getAllBooks()
                .stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
