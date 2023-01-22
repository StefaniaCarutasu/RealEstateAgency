package com.example.real_estate_website.mapper;

import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.models.Role;
import org.springframework.stereotype.Component;


@Component
public class RoleMapper {

    public RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder()
            .id(role.getId())
            .name(role.getName())
            .build();
    }

    public Role mapToRole(RoleDto roleDto) {
        return Role.builder()
            .id(roleDto.getId())
            .name(roleDto.getName())
            .build();
    }
}
