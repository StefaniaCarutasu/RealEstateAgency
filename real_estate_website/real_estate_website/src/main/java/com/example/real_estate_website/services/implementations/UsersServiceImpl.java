package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.RoleDao;
import com.example.real_estate_website.daos.UserDao;
import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.UserMapper;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.services.RoleService;
import com.example.real_estate_website.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean addUser(UserDto userDto) {
        try {
            if (roleService.getRoleByName(userDto.getRole()) == null) {
                throw new RoleNotFoundException("Role with name " + userDto.getRole() + " not found");
            }
            userDao.save(userMapper.mapToUser(userDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AppUser getUserById(Long userId) {
        AppUser user = userDao.getUserById(userId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
    }

    @Override
    public boolean modifyUser(AppUser user) {
        try {
            userDao.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<AppUser> getUsersByRole(String roleName) {
        List<AppUser> appUsers = userDao.findAll();

        return appUsers.stream().filter(user -> user.getRole().getName().equals(roleName)).toList();
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userDao.findAll();
    }
}
