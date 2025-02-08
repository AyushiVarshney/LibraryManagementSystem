package library.models;

import library.decorator.Reservable;

import java.util.Objects;

public class Book implements Reservable {
    private String title;
    private String author;
    private final String isbn;
    private String category;
    private boolean isAvailable = true;
    private boolean reserved;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Book(String title, String author, String isbn, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
    }

    public void borrow() {
        this.isAvailable = false;
    }

    public void returnBook(){
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void reserve(MemberUser member) {
        if (!reserved && !isAvailable) {
            this.reserved = true;
            System.out.println(this.title + " reserved by " + member.getName());
        } else if (reserved) {
            System.out.println(this.title + " is already reserved.");
        } else {
            System.out.println(this.title + " is available for reservation.");
        }
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.title);
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        final Book other = (Book) o;
        return Objects.equals(other.getTitle(), this.getTitle());
    }
}