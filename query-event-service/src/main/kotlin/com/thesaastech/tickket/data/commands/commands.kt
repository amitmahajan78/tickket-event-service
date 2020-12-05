package com.thesaastech.tickket.data.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

data class CreateEvent(@TargetAggregateIdentifier val eventId: UUID, val userId: String, val type: String, val title: String,
                       val description: String, val dataTime: LocalDateTime, val venueAddressLine: String, val venueCity: String,
                       val venuePostcode: String
)



