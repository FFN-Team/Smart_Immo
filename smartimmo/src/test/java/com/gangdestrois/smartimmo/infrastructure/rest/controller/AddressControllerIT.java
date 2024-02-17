package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
class AddressControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressApi addressApi;

    @Test
    void findByPropertyIsNull() throws Exception {
        mockMvc.perform(get("/api/v1/addresses/non-assigned")).andExpect(status().isOk());
    }

    @Test
    void findByPropertyIsNullOrIdIs() throws Exception {
        mockMvc.perform(get("/api/v1/addresses/non-assigned/1")).andExpect(status().isNotFound());
    }
}