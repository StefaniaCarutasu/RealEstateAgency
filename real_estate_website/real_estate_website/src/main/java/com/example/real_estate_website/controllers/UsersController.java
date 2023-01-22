package com.example.real_estate_website.controllers;

import com.example.real_estate_website.dtos.UserDto;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.exceptions.UserNotFoundException;
import com.example.real_estate_website.mapper.UserMapper;
import com.example.real_estate_website.models.AppUser;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.RoleService;
import com.example.real_estate_website.services.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController extends BaseController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "")
    @ResponseBody
    @ApiOperation(
        value = "Retrieves all users",
        response = UserDto.class, responseContainer = "List"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<AppUser> appUsers = usersService.getAllUsers();
            List<UserDto> userDtos = appUsers.stream().map(user -> userMapper.mapToUserDto(user)).toList();
            logAction("Retrieve all users", "UsersController");
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addUser")
    @ResponseBody
    @ApiOperation(
        value = "Adds new user"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> addUser(@RequestBody UserDto user) {
        try {
            boolean isSuccess = usersService.addUser(user);
            if (Boolean.TRUE.equals(isSuccess)) {
                return new ResponseEntity<>("User added successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User was not added.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/changeRole")
    @ResponseBody
    @ApiOperation(
        value = "Adds new role to user"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> changeRole(@RequestParam Long userId, @RequestParam String roleName) {
        try {
            AppUser user = usersService.getUserById(userId);
            Role role = roleService.getRoleByName(roleName);

            if (user.getRole().getName().equals(roleName)) {
                return new ResponseEntity<>("User already in role", HttpStatus.BAD_REQUEST);
            }

            user.setRole(role);

            boolean isSuccess = usersService.modifyUser(user);

            if (Boolean.TRUE.equals(isSuccess)) {
                return new ResponseEntity<>("Role changed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (UserNotFoundException | RoleNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{roleName}")
    @ResponseBody
    @ApiOperation(
        value = "List of users by role"
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> getUsersByRole(@PathVariable String roleName) {
        List<AppUser> appUsers = usersService.getUsersByRole(roleName);

        if (appUsers.isEmpty()) {
            return new ResponseEntity<>("No users found with role " + roleName, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(appUsers, HttpStatus.OK);
        }
    }
}
