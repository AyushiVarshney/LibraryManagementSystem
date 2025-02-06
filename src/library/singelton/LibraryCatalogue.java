package library.singelton;

import library.models.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalogue {
    private static LibraryCatalogue libraryCatalogue = null;
    private final List<Book> bookList = new ArrayList<>();

    private LibraryCatalogue(){}

    public static LibraryCatalogue getLibraryCatalogueInstance(){
        if(libraryCatalogue == null){
            return new LibraryCatalogue();
        }
        return libraryCatalogue;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> getBooks() {
        return this.bookList;
    }
}
