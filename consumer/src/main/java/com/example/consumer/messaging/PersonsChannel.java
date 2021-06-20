package com.example.consumer.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PersonsChannel {
    String PERSONS = "PersonsInputChannel";
    String OTHERS = "OthersInputChannel";
    String ALL = "AllInputChannel";

    @Input(PersonsChannel.PERSONS)
    MessageChannel persons();

    @Input(PersonsChannel.OTHERS)
    MessageChannel others();

    @Input(PersonsChannel.ALL)
    MessageChannel all();
}
