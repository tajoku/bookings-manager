package mt.com.ecabs.bookingsmanager.models;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Booking implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID bookingId;

    @Column
    private String passengerName;

    @Column
    private String passengerContactNumber;

    @Column
    private OffsetDateTime pickupTime;

    @Column
    private Boolean asap = true;

    @Column
    private Integer waitingTime;

    @Column
    private Integer noOfPassengers;

    @Column
    private BigDecimal price;

    @Column
    private Integer rating;

    @Column
    private Instant createdOn;

    @Column
    private Instant lastModifiedOn;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "booking", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<TripWayPoint> tripWayPoints;


    @PrePersist
    @PreUpdate
    void timeUpdate() {
        if (createdOn == null) {
            createdOn = Instant.now();
        }
        lastModifiedOn = Instant.now();
    }
}
