package library.adapter;

import library.models.Book;
import library.models.User;

import java.time.LocalDate;

public interface NotificationService {
    void notifyMemberForBookAvailability(User user, Book book, LocalDate date);
}
