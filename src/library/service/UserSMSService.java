package library.service;

import library.adapter.ExternalSMSService;

public class UserSMSService implements ExternalSMSService {
    @Override
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("[MOCK SMS] To " + phoneNumber + ": " + message);
    }
}
