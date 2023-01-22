package com.example.real_estate_website.controller;

import com.example.real_estate_website.controllers.UsersController;
import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.UserMapper;
import com.example.real_estate_website.mocks.RoleMock;
import com.example.real_estate_website.mocks.UserMock;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.HistoryService;
import com.example.real_estate_website.services.RoleService;
import com.example.real_estate_website.services.implementations.UsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsersController.class)
class UsersControllerTest {

    @MockBean
    private UsersServiceImpl usersService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    HistoryService historyService;
    @MockBean
    UserMapper userMapper;
    @MockBean
    private RoleService roleService;


    @Test
    void testAddUser() throws Exception {
        UserDto userDto = UserMock.mockUserDto();
        String userDtoBody = mapper.writeValueAsString(userDto);

        when(roleService.getRoleByName(userDto.getRole())).thenReturn(RoleMock.mockRole());
        when(usersService.addUser(userDto)).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/addUser")
                .content(userDtoBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        assertEquals("User added successfully.", result.getResponse().getContentAsString());
    }

    @Test
    void testAddUserWithFailure() throws Exception {
        UserDto userDto = UserMock.mockUserDto();
        String userDtoBody = mapper.writeValueAsString(userDto);
        when(usersService.addUser(userDto)).thenReturn(false);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/addUser")
                .content(userDtoBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andReturn();

        assertEquals("User was not added.", result.getResponse().getContentAsString());
    }

    @Test
    void testAddUserWithException() throws Exception {
        UserDto userDto = UserMock.mockUserDto();
        String userDtoBody = mapper.writeValueAsString(userDto);

       when(usersService.addUser(any())).thenThrow(RoleNotFoundException.class);
       mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/addUser")
               .content(userDtoBody)
               .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllUsers() throws Exception {
        AppUser appUser = UserMock.mockUser();
        UserDto userDto = UserMock.mockUserDto();

        when(usersService.getAllUsers()).thenReturn(List.of(appUser));
        when(userMapper.mapToUserDto(appUser)).thenReturn(userDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName").value("Agent1"))
            .andReturn();

        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(List.of(userDto)));
    }

    @Test
    void testGetAllUsersWithException() throws Exception {

        when(usersService.getAllUsers()).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
            .andExpect(status().isInternalServerError());
    }

    @Test
    void testChangeUserRole() throws Exception {
        AppUser user = UserMock.mockBidderUser();
        Role role = RoleMock.mockRole();

        when(usersService.getUserById(5L)).thenReturn(user);
        when(roleService.getRoleByName("AGENT")).thenReturn(role);

        when(usersService.modifyUser(user)).thenReturn(true);

        MvcResult result = mockMvc.perform(put("/api/v1/users/changeRole?userId=5&roleName=AGENT"))
            .andExpect(status().isOk())
            .andReturn();

        assertEquals("Role changed successfully", result.getResponse().getContentAsString());
    }

    @Test
    void testChangeUserRoleAlreadyInRole() throws Exception {

        AppUser user = UserMock.mockBidderUser();
        Role role = RoleMock.mockRole();

        when(usersService.getUserById(5L)).thenReturn(user);
        when(roleService.getRoleByName("BIDDER")).thenReturn(role);

        MvcResult result = mockMvc.perform(put("/api/v1/users/changeRole?userId=5&roleName=BIDDER"))
            .andExpect(status().isBadRequest())
            .andReturn();

        assertEquals("User already in role", result.getResponse().getContentAsString());
    }

    @Test
    void testChangeUserRoleRoleNotFound() throws Exception {

        AppUser user = UserMock.mockBidderUser();

        when(usersService.getUserById(5L)).thenReturn(user);
        when(roleService.getRoleByName("NEW")).thenThrow(new RoleNotFoundException("Role not found"));

        MvcResult result = mockMvc.perform(put("/api/v1/users/changeRole?userId=5&roleName=NEW"))
            .andExpect(status().isBadRequest())
            .andReturn();

        assertEquals("Role not found", result.getResponse().getContentAsString());
    }

    @Test
    void getUsersByRole() throws Exception {

        AppUser user = UserMock.mockUser();

        when(usersService.getUsersByRole("AGENT")).thenReturn(List.of(user));

        MvcResult result = mockMvc.perform(get("/api/v1/users/{roleName}", "AGENT"))
            .andExpect(status().isOk())
            .andReturn();

        assertEquals(result.getResponse().getContentAsString(), mapper.writeValueAsString(List.of(user)));
    }

    @Test
    void getUsersByRoleBadRequest() throws Exception {

        AppUser user = UserMock.mockUser();

        when(usersService.getUsersByRole("OWNER")).thenReturn(Collections.emptyList());

        MvcResult result = mockMvc.perform(get("/api/v1/users/{roleName}", "OWNER"))
            .andExpect(status().isBadRequest())
            .andReturn();

    }
}
