package com.thesaastech.tickket.service.event.web.rest;

import com.thesaastech.tickket.data.commands.CreateEvent;
import com.thesaastech.tickket.data.query.FindDuplicateEvents;
import com.thesaastech.tickket.service.event.web.rest.dto.CreateEventRequestData;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("v1/api")
public class EventCommandResources {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public EventCommandResources(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    @PostMapping(path = "/event")
    public ResponseEntity<String> creatEvent(@Valid @RequestBody CreateEventRequestData createEventRequestData) throws ExecutionException, InterruptedException {
        log.info("Request for event creation..");
        //check duplicate
        CompletableFuture<Boolean> eventResponseData = queryGateway.query(new FindDuplicateEvents(createEventRequestData.getTitle(), createEventRequestData.getType(),
                createEventRequestData.getDateTime(),
                createEventRequestData.getVenueCity()), ResponseTypes.instanceOf(Boolean.class));

        UUID eventId = UUID.randomUUID();

        if (eventResponseData.get()) {

            commandGateway.send(new CreateEvent(eventId, "tomdoo",
                    createEventRequestData.getType(), createEventRequestData.getTitle(), createEventRequestData.getDescription(),
                    createEventRequestData.getDateTime(), createEventRequestData.getVenueAddressLine(), createEventRequestData.getVenueCity(),
                    createEventRequestData.getVenuePostcode()));

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("{ \"eventId\" : \"" + eventId + "\"}");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Duplicate events found. Try creating events with different details.");
    }

}
