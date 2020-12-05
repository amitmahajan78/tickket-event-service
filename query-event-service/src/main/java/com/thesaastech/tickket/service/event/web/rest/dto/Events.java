package com.thesaastech.tickket.service.event.web.rest.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Events {

    private UUID eventId;
    private String type;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String venueAddressLine;
    private String venueCity;
    private String venuePostcode;

}
