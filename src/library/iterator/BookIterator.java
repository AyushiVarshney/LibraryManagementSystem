package library.iterator;

import library.models.Book;

import java.util.Iterator;
import java.util.List;

public class BookIterator implements Iterator<Book> {
    private List<Book> books;
    private int index = 0;

    public BookIterator(List<Book> books){
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return index < books.size();
    }

    @Override
    public Book next(){
        if (hasNext()) {
            Book book = books.get(index);
            index++;
            return book;
        } else {
            throw new java.util.NoSuchElementException("No more books in the catalog.");
        }
    }
}
