package library.adapter;

import library.models.Book;
import library.models.MemberUser;
import library.models.User;

import java.time.LocalDate;

public class SMSAdapter implements NotificationService {
    private final ExternalSMSService smsService;

    public SMSAdapter(ExternalSMSService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void notifyMemberForBookAvailability(User user, Book book, LocalDate date) {
        String phoneNumber = user.getPhoneNumber();
        String message = "Your book '" + book.getTitle() + "' will be available on " + date;
        smsService.sendSMS(phoneNumber, message);
    }
}
