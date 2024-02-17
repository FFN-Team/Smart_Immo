package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PropertyController.class)
class PropertyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyApi propertyApi;

    @MockBean
    private AddressApi addressApi;

    @Test
    void retrieve() throws Exception {
        mockMvc.perform(get("/api/v1/properties")).andExpect(status().isOk());
    }

    @Test
    void collectBuyerById() throws Exception {
        mockMvc.perform(get("/api/v1/properties/1")).andExpect(status().isNotFound());
    }

    @Test
    void save() throws Exception {
        PropertyRequest propertyRequest = new PropertyRequest(
                "Property name",
                "Description",
                4,
                80.0,
                1L
        );

        mockMvc.perform(post("/api/v1/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(propertyRequest.toString())
                        .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() throws Exception {
        PropertyRequest propertyRequest = new PropertyRequest(
                "Property name",
                "Description",
                4,
                80.0,
                1L
        );

        mockMvc.perform(put("/api/v1/properties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(propertyRequest.toString())
                        .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/properties/1")).andExpect(status().isNotFound());
    }
}