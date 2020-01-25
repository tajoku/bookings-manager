package mt.com.ecabs.bookingsmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class TripWayPoint implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "trip_way_point_id", updatable = false, nullable = false)
    private UUID tripWayPointId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    @JsonIgnore
    private Booking booking;

    @Column
    private Boolean lastStop;

    @Column
    private String locality;

    @Column
    private Double lat;

    @Column
    private Double lng;

    @Column
    private Instant tripWayPointTimestamp;
}
