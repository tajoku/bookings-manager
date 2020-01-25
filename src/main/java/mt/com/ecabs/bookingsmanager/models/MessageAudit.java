package mt.com.ecabs.bookingsmanager.models;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "message_audit")
public class MessageAudit implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID auditId;

    @Column
    private String routingKey;

    @Column
    @Lob
    private String message;

    @Column
    private Instant createdOn;

    @PrePersist
    void timeUpdate() {
        if (createdOn == null) {
            createdOn = Instant.now();
        }
    }

}
