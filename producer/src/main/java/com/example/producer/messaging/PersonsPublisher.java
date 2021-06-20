package com.example.producer.messaging;

import com.example.producer.dtos.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(PersonsChannel.class)
@RequiredArgsConstructor
public class PersonsPublisher {
    public static final String ROUTING_KEY = "myRoutingKey";
    private final PersonsChannel source;

    public void sendMessage(Person person, String routingKey) {
        log.info("Sending person: {}", person);
        Message<Person> message = MessageBuilder
                .withPayload(person)
                .setHeader(ROUTING_KEY, routingKey)
                .build();
        source.output().send(message);
        log.info("Message sent successfully");
    }
}
