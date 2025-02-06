package library.factory;

import library.models.LibrarianUser;
import library.models.MemberUser;
import library.models.User;

public class UserFactory {
    public static User createUser(String role, String name, String phoneNumber) {
        if(role == null) return null;
        else if(role.equalsIgnoreCase("MEMBER")) return new MemberUser(name, phoneNumber);
        else if(role.equalsIgnoreCase("LIBRARIAN")) return new LibrarianUser(name);
        else throw new IllegalArgumentException("Unknown User role : " + role);
    }
}
