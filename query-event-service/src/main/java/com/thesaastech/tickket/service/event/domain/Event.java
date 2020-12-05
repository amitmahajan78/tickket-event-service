package com.thesaastech.tickket.service.event.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    private UUID eventId;
    private String type;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String venueAddressLine;
    private String venueCity;
    private String venuePostcode;
}
