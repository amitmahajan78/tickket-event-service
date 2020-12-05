package com.thesaastech.tickket.service.event.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Validated
@ToString
public class CreateEventRequestData implements Serializable {

    private String type;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String venueAddressLine;
    private String venueCity;
    private String venuePostcode;

}
