package org.swiftsms.desktop.service;

import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private String recipient;

    public String getRecipientNumber() {
        return recipient;
    }

    public void setRecipient(final String recipient) {
        this.recipient = recipient;
    }
}
