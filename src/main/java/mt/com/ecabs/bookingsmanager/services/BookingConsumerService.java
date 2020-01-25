package mt.com.ecabs.bookingsmanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mt.com.ecabs.bookingsmanager.dto.BookingDto;
import mt.com.ecabs.bookingsmanager.dto.CreateBookingDto;
import mt.com.ecabs.bookingsmanager.dto.DeleteBookingDto;
import mt.com.ecabs.bookingsmanager.models.MessageAudit;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class BookingConsumerService {

    private BookingsService bookingsService;

    private MessageAuditService messageAuditService;

    private ObjectMapper objectMapper;

    @Autowired
    public void setMessageAuditService(MessageAuditService messageAuditService) {
        this.messageAuditService = messageAuditService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setBookingsService(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @RabbitListener(queues = "bookingAddQueue")
    public void bookingAddConsumer(Message message) throws IOException {
        log.info("Message consumed by add queue: {}", message.getBody());
        bookingsService.saveBooking(objectMapper.readValue(message.getBody(), CreateBookingDto.class));
    }

    @RabbitListener(queues = "bookingEditQueue")
    public void bookingEditConsumer(Message message) throws IOException {
        log.info("Message consumed by edit queue: {}", message.getBody());
        bookingsService.editBooking(objectMapper.readValue(message.getBody(), BookingDto.class));
    }

    @RabbitListener(queues = "bookingDeleteQueue")
    public void bookingDeleteConsumer(Message message) throws IOException {
        log.info("Message consumed by delete queue: {}", message.getBody());
        bookingsService.deleteBooking(objectMapper.readValue(message.getBody(), DeleteBookingDto.class).getBookingId());
    }

    @RabbitListener(queues = "messageAuditQueue")
    public void messageAuditConsumer(Message message) throws IOException {
        log.info("Message consumed by audit queue: {} body {}", message.getMessageProperties().getReceivedRoutingKey(), message);
        messageAuditService.saveMessageAudit(MessageAudit.builder().routingKey(message.getMessageProperties().getReceivedRoutingKey())
                .message(new String(message.getBody())).build());
    }
}
