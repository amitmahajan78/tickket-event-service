package com.thesaastech.tickket.data.query

import java.time.LocalDateTime

data class FindDuplicateEventQuery(val title: String, val type: String, val dataTime: LocalDateTime,
                                   val venueAddressLine: String, val venueCity: String, val venuePostcode: String)