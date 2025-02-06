package library.models;

public class LibrarianUser implements User {
    private String name;
    private String phoneNumber;

    @Override
    public void create(){
        System.out.println("Creating User with Librarian Role");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public String getRole() {
        return "Librarian";
    }

    public LibrarianUser(String name) {
        this.name = name;
    }
}
