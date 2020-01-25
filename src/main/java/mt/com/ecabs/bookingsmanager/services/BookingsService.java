package mt.com.ecabs.bookingsmanager.services;

import lombok.extern.slf4j.Slf4j;
import mt.com.ecabs.bookingsmanager.dto.CreateBookingDto;
import mt.com.ecabs.bookingsmanager.dto.BookingDto;
import mt.com.ecabs.bookingsmanager.dto.TripWayPointDto;
import mt.com.ecabs.bookingsmanager.exceptions.BookingNotFoundException;
import mt.com.ecabs.bookingsmanager.models.Booking;
import mt.com.ecabs.bookingsmanager.models.TripWayPoint;
import mt.com.ecabs.bookingsmanager.repositories.BookingRepository;
import mt.com.ecabs.bookingsmanager.repositories.TripWayPointRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookingsService {

    private BookingRepository bookingRepository;

    private TripWayPointRepository tripWayPointRepository;

    @Autowired
    public void setTripWayPointRepository(TripWayPointRepository tripWayPointRepository) {
        this.tripWayPointRepository = tripWayPointRepository;
    }

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking saveBooking(CreateBookingDto bookingDto) {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking, "tripWayPoints");
        booking.setTripWayPoints(getTripWayPointsForBooking(booking, bookingDto.getTripWayPoints()));
        return bookingRepository.save(booking);
    }

    public Booking editBooking(BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(bookingDto.getBookingId()).orElseThrow(() ->
                new BookingNotFoundException("Booking with specified ID does not exist"));
        BeanUtils.copyProperties(bookingDto, booking, "tripWayPoints");
        tripWayPointRepository.deleteAll(booking.getTripWayPoints());
        booking.setTripWayPoints(getTripWayPointsForBooking(booking, bookingDto.getTripWayPoints()));
        return bookingRepository.save(booking);

    }

    public void deleteBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() ->
                new BookingNotFoundException("Booking with specified ID does not exist"));
        bookingRepository.delete(booking);
    }

    public Page<Booking> getAll(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    private List<TripWayPoint> getTripWayPointsForBooking(Booking booking, List<TripWayPointDto> tripWayPointDtos) {
        List<TripWayPoint> tripWayPoints = new ArrayList<>();
        tripWayPointDtos.forEach(tripWayPointDto -> {
            TripWayPoint tripWayPoint = new TripWayPoint();
            BeanUtils.copyProperties(tripWayPointDto, tripWayPoint);
            tripWayPoint.setBooking(booking);
            tripWayPoints.add(tripWayPoint);
        });
        return tripWayPoints;
    }
}
