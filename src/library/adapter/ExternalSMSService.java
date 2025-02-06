package library.adapter;

public interface ExternalSMSService {
    void sendSMS(String phoneNumber, String message);
}
