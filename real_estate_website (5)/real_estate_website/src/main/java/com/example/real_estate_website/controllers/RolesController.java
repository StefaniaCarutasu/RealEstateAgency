package com.example.real_estate_website.controllers;

import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.mapper.RoleMapper;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesController extends BaseController{

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PostMapping(value = "/addRole/{roleName}")
    @ResponseBody
    @ApiOperation(
        value = "Adds a new role",
        response = ResponseEntity.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> addRole(@PathVariable String roleName) {
        try {
            boolean isSuccess = roleService.addRole(roleName);
            if (Boolean.TRUE.equals(isSuccess)) {
                logAction("Add new role", "RolesController");
                return new ResponseEntity<>("Role added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Could not add role", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRole")
    @ResponseBody
    @ApiOperation(
        value = "Retrieves role with given name",
        response = ResponseEntity.class
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request", response = ResponseEntity.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class),
    })
    public ResponseEntity<Object> getRole(@RequestParam String roleName) {
        try {
            Role role = roleService.getRoleByName(roleName);
            RoleDto roleDto = roleMapper.mapToRoleDto(role);
            logAction("Get role with name: " + roleName, "RolesController");
            return new ResponseEntity<>(roleDto, HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>("Role with name: " + roleName + " not found.", HttpStatus.BAD_REQUEST);
        }
    }
}
