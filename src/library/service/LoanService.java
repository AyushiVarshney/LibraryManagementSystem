package library.service;

import library.models.Book;
import library.models.Loan;
import library.models.MemberUser;
import library.observer.Observable;
import library.observer.Observer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanService implements Observable {
    private final HashMap<String, Book> borrowedBooks = new HashMap<>();
    private final List<Observer> dueDateObservers = new ArrayList<>();
    private final List<Loan> activeLoans = new ArrayList<>();

    /*public boolean borrowBook(String isbn, String user) {
        for(Book book : LibraryCatalogue.getLibraryCatalogueInstance().getBooks()) {
            if(book.getIsbn().equals(isbn) && book.isAvailable()){
                book.borrow();
                borrowedBooks.put(isbn,  book);
                System.out.println(user + " borrowed " + book.getTitle());
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String isbn, String user) {
        if(borrowedBooks.containsKey(isbn)) {
            borrowedBooks.get(isbn).returnBook();
            borrowedBooks.remove(isbn);
            System.out.println(user + " returned " + borrowedBooks.get(isbn).getTitle());
            return true;
        }
        return false;
    }*/

    @Override
    public void addDueDateObserver(Observer observer) {
        dueDateObservers.add(observer);
    }

    @Override
    public void removeDueDateObserver(Observer observer) {
        dueDateObservers.remove(observer);
    }

    @Override
    public void notifyDueSoonObservers(Book book) {
        for (Observer observer : dueDateObservers) {
            observer.update(book, "Your book '" + book.getTitle() + "' is due soon!");
        }
    }

    @Override
    public void notifyOverdueObservers(Book book) {
        for (Observer observer : dueDateObservers) {
            observer.update(book, "Your book '" + book.getTitle() + "' is overdue!");
        }
    }

    public void borrowBook(MemberUser member, Book book) {
        if(book != null) {
            if (book.isAvailable()) {
                LocalDate dueDate = LocalDate.now().plusDays(10);
                Loan loan = new Loan(member, book, dueDate);
                activeLoans.add(loan);
                addDueDateObserver(member);
                book.borrow();
                System.out.println("Book '" + book.getTitle() + "' borrowed by " + member.getName() +
                        ". Due date: " + dueDate);
            } else {
                System.out.println("Book '" + book.getTitle() + "' is not available.");
            }
        } else {
            System.out.println("No such book found");
        }
    }

    public void returnBook(MemberUser member, Book book) {
        Loan loanToRemove = null;
        for (Loan loan : activeLoans) {
            if (loan.getMember().equals(member) && loan.getBook().equals(book)) {
                loan.markAsReturned();
                loanToRemove = loan;
                break;
            }
        }
        if (loanToRemove != null) {
            activeLoans.remove(loanToRemove);
            removeDueDateObserver(member);
            book.returnBook();
            System.out.println("Book '" + book.getTitle() + "' returned by " + member.getName());
        } else {
            System.out.println("Loan record not found for " + member.getName() + " and " + book.getTitle());
        }
    }

    public void checkForDueDates(LocalDate forTestingOnly) {
        for (Loan loan : activeLoans) {
            if (!loan.isReturned()) {
                loan.setDueDate(forTestingOnly);
                if (isOverdue(loan)) {
                    notifyOverdueObservers(loan.getBook());
                } else if (isDueSoon(loan)) {
                    notifyDueSoonObservers(loan.getBook());
                }
            }
        }
    }

    private boolean isDueSoon(Loan loan) {
        return loan.getDueDate().minusDays(3).isBefore(LocalDate.now());
    }

    private boolean isOverdue(Loan loan) {
        return loan.getDueDate().isBefore(LocalDate.now());
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }
}
