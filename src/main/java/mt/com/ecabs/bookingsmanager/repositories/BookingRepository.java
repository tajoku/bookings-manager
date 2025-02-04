package mt.com.ecabs.bookingsmanager.repositories;

import mt.com.ecabs.bookingsmanager.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

}
