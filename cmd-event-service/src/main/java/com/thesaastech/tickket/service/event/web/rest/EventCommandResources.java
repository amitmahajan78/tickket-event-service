package com.thesaastech.tickket.service.event.web.rest;

import com.thesaastech.tickket.data.commands.CreateEvent;
import com.thesaastech.tickket.service.event.web.rest.dto.CreateEventRequestData;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("v1/api")
public class EventCommandResources {

    private final ReactorCommandGateway reactorCommandGateway;
    private final QueryGateway queryGateway;

    public EventCommandResources(ReactorCommandGateway reactorCommandGateway, QueryGateway queryGateway) {
        this.reactorCommandGateway = reactorCommandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping(path = "/event")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UUID> creatEvent(@Valid @RequestBody CreateEventRequestData createEventRequestData) throws ExecutionException, InterruptedException {
        UUID eventId = UUID.randomUUID();

        log.info(createEventRequestData.toString());

       /* CompletableFuture<List<Events>> eventList = queryGateway.query(new FindDuplicateEventQuery(createEventRequestData.getTitle(), createEventRequestData.getType(),
                createEventRequestData.getDateTime(), createEventRequestData.getVenueAddressLine(), createEventRequestData.getVenueCity(),
                createEventRequestData.getVenuePostcode()), ResponseTypes.multipleInstancesOf(Events.class));

        log.info(eventList.get().toString());*/

        return Mono.when(reactorCommandGateway.send(new CreateEvent(eventId, "tomdoo",
                createEventRequestData.getType(), createEventRequestData.getTitle(), createEventRequestData.getDescription(),
                createEventRequestData.getDateTime(), createEventRequestData.getVenueAddressLine(), createEventRequestData.getVenueCity(),
                createEventRequestData.getVenuePostcode())))
                .then(Mono.just(eventId));
    }

}
