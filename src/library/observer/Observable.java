package library.observer;

import library.models.Book;

public interface Observable {
    void addDueDateObserver(Observer observer);
    void removeDueDateObserver(Observer observer);
    void notifyDueSoonObservers(Book book);
    void notifyOverdueObservers(Book book);
}
