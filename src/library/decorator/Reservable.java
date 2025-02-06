package library.decorator;

import library.models.MemberUser;

public interface Reservable {
    void reserve(MemberUser member);
    boolean isReserved();
}
