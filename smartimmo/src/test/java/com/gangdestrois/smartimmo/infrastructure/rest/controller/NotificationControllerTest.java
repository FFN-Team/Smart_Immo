package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.State;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.StateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationApi notificationApi;

    @Test
    void changeState() throws Exception {
        StateRequest stateRequest = new StateRequest(State.OPEN);

        mockMvc.perform(patch("/api/v1/notifications/1/state")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stateRequest.toString())
                        .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }
}