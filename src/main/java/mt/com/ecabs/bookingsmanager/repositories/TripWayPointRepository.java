package mt.com.ecabs.bookingsmanager.repositories;

import mt.com.ecabs.bookingsmanager.models.TripWayPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripWayPointRepository extends JpaRepository<TripWayPoint, UUID> {
}
