package com.thesaastech.tickket.service.event.web.rest;

import com.thesaastech.tickket.data.commands.CreateEvent;
import com.thesaastech.tickket.data.query.FindDuplicateEvents;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
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
    public ResponseEntity<Mono<String>> creatEvent(@Valid @RequestBody CreateEventRequestData createEventRequestData) throws ExecutionException, InterruptedException {
        log.info("adasdadahjhhhajsdahdgaskdgsakdsadgsajkgdaskjdgaksdgsadgskadgsadjkgkdjasgdksajgdkj");
        //check duplicate
        CompletableFuture<Boolean> eventResponseData = queryGateway.query(new FindDuplicateEvents(createEventRequestData.getTitle(), createEventRequestData.getType(),
                createEventRequestData.getDateTime(),
                createEventRequestData.getVenueCity()), ResponseTypes.instanceOf(Boolean.class));

        UUID eventId = UUID.randomUUID();

        if (eventResponseData.get()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(Mono.when(reactorCommandGateway.send(new CreateEvent(eventId, "tomdoo",
                            createEventRequestData.getType(), createEventRequestData.getTitle(), createEventRequestData.getDescription(),
                            createEventRequestData.getDateTime(), createEventRequestData.getVenueAddressLine(), createEventRequestData.getVenueCity(),
                            createEventRequestData.getVenuePostcode())))
                            .then(Mono.just(eventId.toString())));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Mono.just("Duplicate events found. Try creating events with different details."));
    }

}
