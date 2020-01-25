package mt.com.ecabs.bookingsmanager.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class DeleteBookingDto implements Serializable {
    private UUID bookingId;
}
