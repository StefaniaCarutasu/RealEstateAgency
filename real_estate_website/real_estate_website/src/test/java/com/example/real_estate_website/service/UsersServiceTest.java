package com.example.real_estate_website.service;

import com.example.real_estate_website.daos.RoleDao;
import com.example.real_estate_website.daos.UserDao;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.UserMapper;
import com.example.real_estate_website.mocks.UserMock;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.services.implementations.RoleServiceImpl;
import com.example.real_estate_website.services.implementations.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UsersServiceTest {

    @InjectMocks
    private UsersServiceImpl usersService;
    @Mock
    private UserDao userDao;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDao roleDao;
//
//    @Test
//    void testAddUser() {
//        //GIVEN
//        UserDto userDto = UserMock.mockUserDto();
//        AppUser user = UserMock.mockUser();
//        Role role = RoleMock.mockRole();
//
//        //WHEN
//        when(roleDao.getRoleByName(userDto.getRole())).thenReturn(role);
//        when(roleService.getRoleByName(userDto.getRole())).thenReturn(role);
//        when(userMapper.mapToUser(userDto)).thenReturn(user);
//        when(userDao.save(user)).thenReturn(user);
//
//        boolean result = usersService.addUser(userDto);
//
//        //THEN
//        assertEquals(Boolean.TRUE, result);
//    }

    @Test
    void testUserById() {
        //GIVEN
        AppUser user = UserMock.mockUser();

        //WHEN
        when(userDao.getUserById(3L)).thenReturn(user);
        AppUser result = usersService.getUserById(3L);

        //THEN
        assertNotNull(result);
        assertEquals(user.getRole(), result.getRole());
    }

    @Test
    void testUserByIdNotFoundException() {
        //WHEN
        when(userDao.getUserById(3L)).thenReturn(null);

        UserNotFoundException userNotFoundException =
            assertThrows(UserNotFoundException.class , () -> usersService.getUserById(3L));

        //THEN
        assertEquals("User with id 3 not found", userNotFoundException.getMessage());

    }
}
