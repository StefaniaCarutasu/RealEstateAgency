package com.example.real_estate_website.service;

import com.example.real_estate_website.daos.RoleDao;
import com.example.real_estate_website.mocks.RoleMock;
import com.example.real_estate_website.models.Role;;
import com.example.real_estate_website.services.implementations.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDao roleDao;


    @Test
    void testGetRoleByName() {
        //GIVEN
        Role mockRole = RoleMock.mockRole();

        //WHEN
        when(roleDao.getRoleByName("AGENT")).thenReturn(mockRole);
        Role result = roleService.getRoleByName("AGENT");

        //THEN
        assertEquals(result.getName(), "AGENT");
    }

    @Test
    void testGetRoleById() {
        //GIVEN
        Role mockRole = RoleMock.mockRole();

        //WHEN
        when(roleDao.getRoleById(1L)).thenReturn(mockRole);
        Role result = roleService.getRoleById(1L);

        //THEN
        assertNotNull(result);
    }

}
