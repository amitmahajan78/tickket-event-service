package com.thesaastech.tickket.service.event.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CreateEventResponseData {

    private String eventId;
    private String message;
}
