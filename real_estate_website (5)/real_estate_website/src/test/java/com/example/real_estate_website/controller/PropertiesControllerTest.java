package com.example.real_estate_website.controller;

import com.example.real_estate_website.controllers.PropertiesController;
import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.exceptions.UserNotInRoleException;
import com.example.real_estate_website.mapper.PropertyMapper;
import com.example.real_estate_website.mocks.PropertyMock;
import com.example.real_estate_website.mocks.UserMock;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.services.HistoryService;
import com.example.real_estate_website.services.implementations.PropertiesServiceImpl;
import com.example.real_estate_website.services.implementations.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PropertiesController.class)
class PropertiesControllerTest {

    @MockBean
    private PropertiesServiceImpl propertiesService;
    @MockBean
    private UsersServiceImpl usersService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    HistoryService historyService;
    @MockBean
    PropertyMapper propertyMapper;

    @Test
    void testAddProperty() throws Exception {
        PropertyDto propertyDto = PropertyMock.mockPropertyDto();
        AppUser appUser = UserMock.mockUser();
        String propertyDtoBody = mapper.writeValueAsString(propertyDto);
        when(usersService.getUserById(appUser.getId())).thenReturn(appUser);
        when(propertiesService.addProperty(any(), any())).thenReturn(true);
        MvcResult result = mockMvc.perform(post("/api/v1/properties/addProperty?userId=" + appUser.getId())
                .content(propertyDtoBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
        assertEquals("Property added successfully", result.getResponse().getContentAsString());
    }

    @Test
    void testAddPropertyWithException() throws Exception {
        PropertyDto propertyDto = PropertyMock.mockPropertyDto();
        String propertyDtoBody = mapper.writeValueAsString(propertyDto);
        when(usersService.getUserById(888L)).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/api/v1/properties/addProperty?userId=8888L")
                .content(propertyDtoBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
    }

    @Test
    void testGetPropertiesByOwner() throws Exception {
        Property property = PropertyMock.mockProperty();
        when(propertiesService.getAllPropertiesForOwner(4L)).thenReturn(List.of(property));
        MvcResult result = mockMvc.perform(get("/api/v1/properties/getProperties?ownerId=4"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].address").value(property.getAddress()))
            .andReturn();
        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(List.of(property)));
    }

    @Test
    void testGetPropertiesByOwnerWithException() throws Exception {
        when(propertiesService.getAllPropertiesForOwner(any())).thenThrow(UserNotInRoleException.class);
        mockMvc.perform(get("/api/v1/properties/getProperties?ownerId=5")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

}
