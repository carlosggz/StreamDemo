package com.example.producer.messaging;

import com.example.producer.dtos.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.cloud.stream.test.matcher.MessageQueueMatcher.receivesPayloadThat;

@SpringBootTest
class PersonsPublisherTest {

    @Autowired
    MessageCollector messageCollector;

    @Autowired
    PersonsChannel personsChannel;

    @Autowired
    PersonsPublisher personsPublisher;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    void sendMessagePutsAMessageInQueue() {
        val routingKey = "myKey";
        val person = new Person("123", "name", 44);
        val expectedMessageContent = objectMapper.writeValueAsString(person);

        personsPublisher.sendMessage(person, routingKey);

        val messages = messageCollector.forChannel(personsChannel.output());

        assertEquals(1, messages.size());
        val message = messages.take();

        val header = message.getHeaders().get(PersonsPublisher.ROUTING_KEY);
        assertNotNull(header);
        assertEquals(routingKey, header);

        assertEquals(expectedMessageContent, message.getPayload().toString());

        //other way
        //assertThat(messages, receivesPayloadThat(is(expectedMessageContent)));
    }

}