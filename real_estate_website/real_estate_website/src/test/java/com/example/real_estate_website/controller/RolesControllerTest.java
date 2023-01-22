package com.example.real_estate_website.controller;

import com.example.real_estate_website.controllers.RolesController;
import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.exceptions.UserNotInRoleException;
import com.example.real_estate_website.mapper.RoleMapper;
import com.example.real_estate_website.mocks.RoleMock;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.HistoryService;
import com.example.real_estate_website.services.implementations.RoleServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RolesController.class)
class RolesControllerTest {

    @MockBean
    private RoleServiceImpl roleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    HistoryService historyService;
    @MockBean
    RoleMapper roleMapper;

    @Test
    void testAddRole() throws Exception {
        String roleName = RoleMock.mockRole().getName();
        when(roleService.addRole(any())).thenReturn(true);
        MvcResult result = mockMvc.perform(post("/api/v1/roles/addRole/" + roleName))
            .andExpect(status().isOk())
            .andReturn();
        assertEquals("Role added successfully", result.getResponse().getContentAsString());
    }

    @Test
    void testAddRoleWithException() throws Exception {
        when(roleService.addRole(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/api/v1/roles/addRole/{roleName}", "NORMAL"))
            .andExpect(status().isInternalServerError());
    }

    @Test
    void testAddRoleWithFailure() throws Exception {
        when(roleService.addRole(any())).thenReturn(false);
        MvcResult result = mockMvc.perform(post("/api/v1/roles/addRole/{roleName}", "NORMAL"))
            .andExpect(status().isInternalServerError())
            .andReturn();
        assertEquals("Could not add role", result.getResponse().getContentAsString());
    }

    @Test
    void testGetRoleByName() throws Exception {
        RoleDto roleDto = RoleMock.mockRoleDto();
        Role role = RoleMock.mockRole();

        when(roleService.getRoleByName("AGENT")).thenReturn(role);
        when(roleMapper.mapToRoleDto(role)).thenReturn(roleDto);
        String roleDtoBody = mapper.writeValueAsString(roleDto);

        MvcResult result = mockMvc.perform(get("/api/v1/roles/getRole?roleName=" + role.getName())
                .content(roleDtoBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("AGENT"))
            .andReturn();
        assertEquals(roleDtoBody, result.getResponse().getContentAsString());
    }

    @Test
    void testGetRoleByNameWithException() throws Exception {
        when(roleService.getRoleByName("NORMAL")).thenThrow(RoleNotFoundException.class);
        mockMvc.perform(get("/api/v1/roles/getRole?roleName=NORMAL")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

}
