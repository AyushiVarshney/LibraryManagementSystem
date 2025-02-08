package library.models;

import java.time.LocalDate;

public class Loan {
    private MemberUser member;
    private Book book;
    private LocalDate dueDate;
    private boolean returned; // Flag to track if the book has been returned

    public Loan(MemberUser member, Book book, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.dueDate = dueDate;
        this.returned = false; // Initially, the book is not returned
    }

    public MemberUser getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markAsReturned() {
        this.returned = true;
    }

    @Override
    public String toString() { // Good practice for debugging/logging
        return "Loan{" +
                "member=" + member.getName() +
                ", book=" + book.getTitle() +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                '}';
    }
}
