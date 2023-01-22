package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.RoleDao;
import com.example.real_estate_website.exceptions.RoleNotFoundException;
import com.example.real_estate_website.models.Role;
import com.example.real_estate_website.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleById(Long roleId) {
       Role role = roleDao.getRoleById(roleId);

       if (role != null) {
           return role;
       } else {
           throw new RoleNotFoundException("Role not found");
       }
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role role = roleDao.getRoleByName(roleName);

        if (role != null) {
            return role;
        } else {
            throw new RoleNotFoundException("Role not found");
        }
    }

    @Override
    public boolean addRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        try {
            roleDao.save(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
