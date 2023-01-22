package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<AppUser, Long> {

    @Query(value = "SELECT u FROM AppUser u")
    List<AppUser> getAllUsers();

    @Query(value = "SELECT u FROM AppUser u where u.id = ?1")
    AppUser getUserById(Long userId);

    @Query(value = "SELECT u FROM AppUser u")
    ArrayList<AppUser> getUsersByRole(String roleName);

}
