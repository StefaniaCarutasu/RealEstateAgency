package com.example.real_estate_website.mocks;

import com.example.real_estate_website.dtos.RoleDto;
import com.example.real_estate_website.models.Role;

public class RoleMock {

    public static Role mockRole() {
        return Role.builder()
            .id(1L)
            .name("AGENT")
            .build();
    }

    public static RoleDto mockRoleDto() {
        return RoleDto.builder()
            .id(1L)
            .name("AGENT")
            .build();
    }
}
