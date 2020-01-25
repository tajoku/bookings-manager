package mt.com.ecabs.bookingsmanager.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {


    @Bean
    Queue messageAuditQueue() {
        return new Queue("messageAuditQueue", false);
    }

    @Bean
    Queue bookingAddQueue() {
        return new Queue("bookingAddQueue", false);
    }

    @Bean
    Queue bookingEditQueue() {
        return new Queue("bookingEditQueue", false);
    }

    @Bean
    Queue bookingDeleteQueue() {
        return new Queue("bookingDeleteQueue", false);
    }

    @Bean
    DirectExchange bookingExchange() {
        return new DirectExchange("booking-exchange");
    }

    @Bean
    TopicExchange messageExchange() {
        return new TopicExchange("message-exchange");
    }

    @Bean
    Binding messageAuditBinding(Queue messageAuditQueue, TopicExchange exchange) {
        return BindingBuilder.bind(messageAuditQueue).to(exchange).with("booking.*");
    }

    @Bean
    Binding bookingAddBinding(Queue bookingAddQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookingAddQueue).to(exchange).with("booking.add");
    }

    @Bean
    Binding bookingEditBinding(Queue bookingEditQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookingEditQueue).to(exchange).with("booking.edit");
    }

    @Bean
    Binding bookingDeleteBinding(Queue bookingDeleteQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookingDeleteQueue).to(exchange).with("booking.delete");
    }
    
    @Bean
    Binding bookingExchangeBinding(DirectExchange bookingExchange, TopicExchange messageExchange) {
        return BindingBuilder.bind(bookingExchange).to(messageExchange).with("booking.*");
    }


}
