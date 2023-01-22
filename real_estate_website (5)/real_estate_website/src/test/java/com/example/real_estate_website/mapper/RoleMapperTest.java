package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.PropertyDto;
import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.mocks.PropertyMock;
import com.example.real_estate_website.mocks.RoleMock;
import com.example.real_estate_website.models.Property;
import com.example.real_estate_website.models.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RoleMapperTest {

    @InjectMocks
    RoleMapper roleMapper;

    @Test
    void mapToRoleTest(){
        //GIVEN
        RoleDto roleDto = RoleMock.mockRoleDto();

        //WHEN
        Role role = roleMapper.mapToRole(roleDto);

        //THEN
        assertEquals(role.getName(), roleDto.getName());
        assertNotNull(role.getId());
    }

    @Test
    void mapToPropertyDtoTest(){
        //GIVEN
        Role role = RoleMock.mockRole();

        //WHEN
        RoleDto roleDto = roleMapper.mapToRoleDto(role);

        //THEN
        assertEquals(role.getName(), roleDto.getName());
        assertNotNull(roleDto.getId());
    }
}
