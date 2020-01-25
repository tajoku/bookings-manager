package mt.com.ecabs.bookingsmanager.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingProducerService {

    private AmqpTemplate amqpTemplate;

    private String defaultExchange;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${booking.default.exchange:message-exchange}")
    public void setDefaultExchange(String defaultExchange) {
        this.defaultExchange = defaultExchange;
    }

    public boolean sendMessageToQueue(String routingKey, Object message) throws JsonProcessingException {
        amqpTemplate.convertAndSend(defaultExchange, routingKey, objectMapper.writeValueAsString(message));
        return true;
    }
}
