package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    @Query(value = "SELECT r FROM Role r where r.id = ?1")
    Role getRoleById(Long roleId);

    @Query(value = "SELECT r FROM Role r where r.name = ?1")
    Role getRoleByName(String roleName);
}
