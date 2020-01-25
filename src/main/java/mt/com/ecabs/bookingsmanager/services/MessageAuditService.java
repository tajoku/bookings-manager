package mt.com.ecabs.bookingsmanager.services;

import mt.com.ecabs.bookingsmanager.models.MessageAudit;
import mt.com.ecabs.bookingsmanager.repositories.MessageAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageAuditService {

    private MessageAuditRepository messageAuditRepository;

    @Autowired
    public void setMessageAuditRepository(MessageAuditRepository messageAuditRepository) {
        this.messageAuditRepository = messageAuditRepository;
    }

    public MessageAudit saveMessageAudit(MessageAudit messageAudit) {
        return messageAuditRepository.save(messageAudit);
    }
}
