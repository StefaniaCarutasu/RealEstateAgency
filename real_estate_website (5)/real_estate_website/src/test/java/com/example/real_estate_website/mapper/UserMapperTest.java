package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.mocks.RoleMock;
import com.example.real_estate_website.mocks.UserMock;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.implementations.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Mock
    RoleServiceImpl roleService;

    @Test
    void mapToUserTest(){
        //GIVEN
        UserDto userDto = UserMock.mockUserDto();
        Role role = RoleMock.mockRole();

        //WHEN
        when(roleService.getRoleByName(userDto.getRole())).thenReturn(role);
        AppUser user = userMapper.mapToUser(userDto);

        //THEN
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertNotNull(user.getRole());
    }

    @Test
    void mapToUserDtoTest(){
        //GIVEN
        AppUser user = UserMock.mockUser();

        //WHEN
        UserDto userDto = userMapper.mapToUserDto(user);

        //THEN
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertNotNull(user.getRole());
    }
}
