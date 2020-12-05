package com.thesaastech.tickket.service.event.web.test;


import com.thesaastech.tickket.service.event.web.rest.dto.CreateEventRequestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventCommandResourcesTest {

    private final static String LOCALHOST = "http://localhost:10344/";
    private MockMvc restMockMvc;

    @BeforeEach
    void setup() {
        restMockMvc = MockMvcBuilders.standaloneSetup().build();
    }

    @Test
    void createEventTest() throws Exception {

        CreateEventRequestData createEventRequestData = new CreateEventRequestData();

        createEventRequestData.setDateTime(LocalDateTime.now());
        createEventRequestData.setDescription("One day match between India and Australia");
        createEventRequestData.setTitle("IND-AUS ODI");
        createEventRequestData.setType("SPORT");
        createEventRequestData.setVenueAddressLine("Cardiff Stadium");
        createEventRequestData.setVenueCity("Cardiff");
        createEventRequestData.setVenuePostcode("CF102FG");

        restMockMvc.perform(post(LOCALHOST + "v1/api/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonString(createEventRequestData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
