package com.thesaastech.tickket.data.query

import java.time.LocalDateTime

data class FindDuplicateEvents(val title: String, val type: String, val dataTime: LocalDateTime,
                               val venueCity: String)

data class ListEvents(val title: String? = null, val type: String? = null,
                      val venueCity: String? = null, val status: String? = null, val userId: String? = null)
