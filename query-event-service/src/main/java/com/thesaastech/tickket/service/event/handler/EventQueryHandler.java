package com.thesaastech.tickket.service.event.handler;


import com.thesaastech.tickket.data.query.FindDuplicateEvents;
import com.thesaastech.tickket.service.event.domain.Event;
import com.thesaastech.tickket.service.event.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventQueryHandler {

    private final EventRepository eventRepository;


    public EventQueryHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @QueryHandler
    Boolean handle(FindDuplicateEvents findDuplicateEvents) {

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matchingAll()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("venue_city", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Event example = Event.builder()
                .title(findDuplicateEvents.getTitle())
                .type(findDuplicateEvents.getType())
                .venueCity(findDuplicateEvents.getVenueCity())
                .dateTime(findDuplicateEvents.getDataTime())
                .build();

        log.info("Duplicate Query check completed :: " + example.toString());

        return (eventRepository.findAll(Example.of(example, exampleMatcher))).isEmpty();
    }
}
