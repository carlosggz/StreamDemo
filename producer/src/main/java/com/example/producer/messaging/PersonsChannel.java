package com.example.producer.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PersonsChannel {
    String OUTPUT = "PersonsOutputChannel";

    @Output(PersonsChannel.OUTPUT)
    MessageChannel output();
}
