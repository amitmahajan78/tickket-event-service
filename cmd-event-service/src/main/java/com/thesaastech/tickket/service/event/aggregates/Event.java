package com.thesaastech.tickket.service.event.aggregates;

import com.thesaastech.tickket.data.EventStatus;
import com.thesaastech.tickket.data.commands.CreateEvent;
import com.thesaastech.tickket.data.events.EventCreated;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class Event {

    @AggregateIdentifier
    private UUID eventId;
    private String eventStatus;

    private Event() {

    }

    @CommandHandler
    Event(CreateEvent createEvent) {
        apply(new EventCreated(createEvent.getEventId(),createEvent.getUserId(), createEvent.getType(),
            createEvent.getTitle(), createEvent.getDescription(), createEvent.getDataTime(), createEvent.getVenueAddressLine(),
            createEvent.getVenueCity(), createEvent.getVenuePostcode(), EventStatus.NEW.toString()));
    }

    @EventSourcingHandler
    void on(EventCreated eventCreated)
    {
        this.eventId = eventCreated.getEventId();
        this.eventStatus = eventCreated.getStatus();
    }

}
