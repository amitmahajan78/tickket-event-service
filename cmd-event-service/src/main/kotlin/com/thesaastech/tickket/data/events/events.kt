package com.thesaastech.tickket.data.events

import java.time.Instant
import java.time.LocalDateTime
import java.util.*

data class EventCreated(
    val eventId: UUID, val userId: String, val type: String, val title: String,
    val description: String, val dataTime: LocalDateTime, val venueAddressLine: String, val venueCity: String,
    val venuePostcode: String, val status: String
)
