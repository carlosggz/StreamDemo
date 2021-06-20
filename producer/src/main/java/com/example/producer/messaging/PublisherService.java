package com.example.producer.messaging;

import com.example.producer.dtos.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true)
public class PublisherService {

    private final PersonsPublisher personsPublisher;

    @Scheduled(fixedDelay = 3000)
    public void producer() {
        sendMessage("first.message.key");
        sendMessage("second.message.key");
        sendMessage("third.message.key");
        sendMessage("fourth.message.key");
    }

    private void sendMessage(String routingKey) {
        String id = UUID.randomUUID().toString();
        Person person = new Person(id, routingKey + "-" + id, LocalDateTime.now().getSecond());
        personsPublisher.sendMessage(person, routingKey);
    }
}
