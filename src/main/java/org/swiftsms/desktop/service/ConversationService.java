package org.swiftsms.desktop.service;

import org.springframework.stereotype.Service;
import org.swiftsms.model.Contact;

@Service
public class ConversationService {

    private Contact recipient;

    public String getRecipientNumber() {
        return recipient.getNumber();
    }

    public Contact getRecipient() {
        return recipient;
    }

    public void setRecipient(final String recipient) {
        if (this.recipient == null) {
            this.recipient = new Contact(recipient, "Name", "Last");
        }
        this.recipient.setNumber(recipient);
    }
}
