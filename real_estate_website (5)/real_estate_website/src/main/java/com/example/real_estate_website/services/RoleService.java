package com.example.real_estate_website.services;

import com.example.real_estate_website.models.Role;

public interface RoleService {

    Role getRoleById(Long roleId);

    Role getRoleByName(String roleName);

    boolean addRole(String roleName);
}
