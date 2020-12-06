package com.thesaastech.tickket.service.event.handler;

import com.thesaastech.tickket.data.events.EventCreated;
import com.thesaastech.tickket.data.query.ListEvents;
import com.thesaastech.tickket.service.event.domain.Event;
import com.thesaastech.tickket.service.event.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EventsHandler {

    private final EventRepository eventRepository;
    private final QueryUpdateEmitter queryUpdateEmitter;

    public EventsHandler(EventRepository eventRepository, QueryUpdateEmitter queryUpdateEmitter) {
        this.eventRepository = eventRepository;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    void on(EventCreated eventCreated) {
        Event event = Event.builder()
                .eventId(eventCreated.getEventId())
                .dateTime(eventCreated.getDataTime())
                .description(eventCreated.getDescription())
                .title(eventCreated.getTitle())
                .type(eventCreated.getType())
                .venueAddressLine(eventCreated.getVenueAddressLine())
                .venueCity(eventCreated.getVenueCity())
                .venuePostcode(eventCreated.getVenuePostcode())
                .status(eventCreated.getStatus())
                .userId(eventCreated.getUserId())
                .build();
        this.eventRepository.save(event);
    }

    @QueryHandler
    List<Event> handle(ListEvents listEvents) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAll()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Event example = Event.builder()
                .title(listEvents.getTitle())
                .status(listEvents.getStatus())
                .type(listEvents.getType())
                .venueCity(listEvents.getVenueCity())
                .userId(listEvents.getUserId())
                .build();
        log.info(example.toString());
        return eventRepository.findAll(Example.of(example, exampleMatcher));
    }


}
