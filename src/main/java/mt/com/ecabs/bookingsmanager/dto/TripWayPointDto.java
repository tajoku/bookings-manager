package mt.com.ecabs.bookingsmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripWayPointDto implements Serializable {

    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;
    private Instant tripWayPointTimestamp;
}
