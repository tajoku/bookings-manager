package mt.com.ecabs.bookingsmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookingDto implements Serializable {
    private String passengerName;
    private String passengerContactNumber;
    private OffsetDateTime pickupTime;
    private Boolean asap = true;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;
    private List<TripWayPointDto> tripWayPoints;
}
