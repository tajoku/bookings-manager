package mt.com.ecabs.bookingsmanager.controllers;

import mt.com.ecabs.bookingsmanager.api.ApiResponse;
import mt.com.ecabs.bookingsmanager.dto.CreateBookingDto;
import mt.com.ecabs.bookingsmanager.dto.BookingDto;
import mt.com.ecabs.bookingsmanager.models.Booking;
import mt.com.ecabs.bookingsmanager.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingsService bookingsService;

    @Autowired
    public void setBookingsService(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("/save")
    public ApiResponse<Booking> addBooking(@RequestBody CreateBookingDto createBookingDto) {
        return new ApiResponse<>(true, bookingsService.saveBooking(createBookingDto));
    }

    @GetMapping("/all")
    public ApiResponse<Page<Booking>> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size) {
        return new ApiResponse<>(true, bookingsService.getAll(page, size));
    }

    @PutMapping("/edit")
    public ApiResponse<Booking> editBooking(@RequestBody @Valid BookingDto bookingDto) {
        return new ApiResponse<>(true, bookingsService.editBooking(bookingDto));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBooking(@PathVariable UUID id) {
        bookingsService.deleteBooking(id);
        return new ApiResponse<>(true);
    }
}
