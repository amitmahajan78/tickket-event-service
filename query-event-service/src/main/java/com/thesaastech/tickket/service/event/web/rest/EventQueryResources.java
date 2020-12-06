package com.thesaastech.tickket.service.event.web.rest;

import com.thesaastech.tickket.data.query.ListEvents;
import com.thesaastech.tickket.service.event.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("v1/api")
public class EventQueryResources {

    private final ReactorQueryGateway reactorQueryGateway;

    public EventQueryResources(ReactorQueryGateway reactorQueryGateway) {
        this.reactorQueryGateway = reactorQueryGateway;
    }

    @GetMapping("/event")
    public Mono<List<Event>> ListAccount(@RequestParam(required = false) String title, @RequestParam(required = false) String type,
                                         @RequestParam(required = false) String city,
                                         @RequestParam(required = false) String status) {
        return reactorQueryGateway.query(new ListEvents(title, type, city, status, "tomdoo"),
                ResponseTypes.multipleInstancesOf(Event.class));
    }
}
