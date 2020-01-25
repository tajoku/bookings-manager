package mt.com.ecabs.bookingsmanager.repositories;

import mt.com.ecabs.bookingsmanager.models.MessageAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageAuditRepository extends JpaRepository<MessageAudit, UUID> {

}
