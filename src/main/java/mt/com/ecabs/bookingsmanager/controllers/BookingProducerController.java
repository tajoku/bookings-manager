package mt.com.ecabs.bookingsmanager.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import mt.com.ecabs.bookingsmanager.services.BookingProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@Slf4j
public class BookingProducerController {

    private BookingProducerService bookingProducerService;

    @Autowired
    public void setBookingProducerService(BookingProducerService bookingProducerService) {
        this.bookingProducerService = bookingProducerService;
    }

    @PostMapping("/send")
    public boolean sendMessage(@RequestParam("routingKey") String routingKey,
                               @RequestBody Object messageData) {

        try {
            bookingProducerService.sendMessageToQueue(routingKey, messageData);

            return true;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);

            return false;
        }
    }
}
