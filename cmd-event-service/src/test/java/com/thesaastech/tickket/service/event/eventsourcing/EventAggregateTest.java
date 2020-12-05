package com.thesaastech.tickket.service.event.eventsourcing;

import com.thesaastech.tickket.data.EventStatus;
import com.thesaastech.tickket.data.EventType;
import com.thesaastech.tickket.data.commands.CreateEvent;
import com.thesaastech.tickket.data.events.EventCreated;
import com.thesaastech.tickket.service.event.aggregates.Event;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

class EventAggregateTest {

    public static final String USER_ID = "tomdoo";
    public static final String TITLE = "Colour of Indian Music";
    public static final String DESCRIPTION = "verity of indian music";
    public static final String ADDRESS_LINE = "Albert Hall";
    public static final String CITY = "London";
    public static final String POSTCODE = "EC23FG";
    private AggregateTestFixture<Event> testFixture;


    @BeforeEach
    void setUp() {
        testFixture = new AggregateTestFixture<>(Event.class);
    }

    @Test
    void createEventTest() {
        UUID eventId = UUID.randomUUID();

        LocalDateTime eventDateTime = LocalDateTime.now();

        CreateEvent createEvent = new CreateEvent(eventId, USER_ID, EventType.CONCERT.toString(),
                TITLE, DESCRIPTION, eventDateTime, ADDRESS_LINE,
                CITY, POSTCODE);

        EventCreated eventCreated = new EventCreated(eventId, USER_ID, EventType.CONCERT.toString(),
                TITLE, DESCRIPTION, eventDateTime, ADDRESS_LINE,
                CITY, POSTCODE, EventStatus.NEW.toString());

        testFixture.givenNoPriorActivity()
                .when(createEvent)
                .expectEvents(eventCreated)
                .expectSuccessfulHandlerExecution();
    }

}
