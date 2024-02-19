package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
class NotificationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationApi notificationApi;

    @Test
    void changeStatus() throws Exception {
        NotificationStatusRequest notificationStatusRequest = new NotificationStatusRequest(NotificationStatus.OPEN);

        mockMvc.perform(patch("/api/v1/notifications/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notificationStatusRequest.toString())
                        .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }
}