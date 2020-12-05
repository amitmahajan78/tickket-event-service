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
public class EventQueryResources {


}
