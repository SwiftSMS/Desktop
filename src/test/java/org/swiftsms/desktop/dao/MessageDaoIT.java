package org.swiftsms.desktop.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.swiftsms.desktop.IntegrationTestConfig;
import org.swiftsms.model.Contact;
import org.swiftsms.model.Message;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MessageDaoIT extends IntegrationTestConfig {

    @Autowired
    private MessageDao messageDao;

    @Test
    @Ignore
    public void testGetHistory() throws Exception {
    }

    @Test
    @Ignore
    public void testGetConversation() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        final Set<Contact> contacts = new HashSet<>();
        final Contact contact = new Contact("123", "Sean", "Dunne");
        contacts.add(contact);

        final Message message = new Message();
        message.setRecipients(contacts);
        message.setMessage("My message.");

        messageDao.save(message);

        final List<Contact> recentContacts = messageDao.getRecentContacts();
        assertEquals(1, recentContacts.size());
    }
}