package library.decorator;

import library.models.Book;
import library.models.MemberUser;

public class ReservableBookDecorator implements Reservable {
    private Book book;

    public ReservableBookDecorator(Book book) {
        this.book = book;
    }

    @Override
    public void reserve(MemberUser member) {
        book.reserve(member);
    }

    @Override
    public boolean isReserved() {
        return book.isReserved();
    }

    public String getISBN() { return book.getIsbn(); }
    public String getTitle() { return book.getTitle(); }
    public String getCategory() { return book.getCategory(); }
    public String getAuthor() { return book.getAuthor(); }

    public void setAvailable(boolean available) {
        //book.set(available);
    }

    public boolean isAvailable() {
        return book.isAvailable();
    }
}
