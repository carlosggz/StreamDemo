package com.example.consumer.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(PersonsChannel.class)
@RequiredArgsConstructor
public class PersonsConsumer {

    @StreamListener(target = PersonsChannel.PERSONS)
    public void consumePersons(String person) {
        log.info("Received person on channel {}: {}", PersonsChannel.PERSONS, person);
    }

    @StreamListener(target = PersonsChannel.OTHERS)
    public void consumeOthers(String person) {
        log.info("Received person on channel {}: {}", PersonsChannel.OTHERS, person);
    }

    @StreamListener(target = PersonsChannel.ALL)
    public void consumeAll(String person) {
        log.info("Received person on channel {}: {}", PersonsChannel.ALL, person);
    }
}
