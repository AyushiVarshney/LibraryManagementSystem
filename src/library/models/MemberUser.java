package library.models;

import library.observer.Observer;

public class MemberUser implements User, Observer {
    private String name;
    private String phoneNumber;

    @Override
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    @Override
    public void create(){
        System.out.println("Creating User with Member Role");
    }

    @Override
    public void update(Book book, String message) {
        System.out.println(name + ", you have a notification: " + message + " for book: " + book.getTitle());
    }

    public MemberUser(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
