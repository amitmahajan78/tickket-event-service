package com.thesaastech.tickket.service.event.web.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class EventResponseData {

    private UUID eventId;
    private String type;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String venueAddressLine;
    private String venueCity;
    private String venuePostcode;
    private String status;

}
