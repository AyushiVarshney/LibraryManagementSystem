package library.observer;

import library.models.Book;

public interface Observer {
    void update(Book book, String message);
}
