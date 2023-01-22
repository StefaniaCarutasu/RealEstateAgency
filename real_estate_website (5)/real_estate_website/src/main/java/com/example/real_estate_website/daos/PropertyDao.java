package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyDao extends JpaRepository<Property, Long> {

    @Query(value = "SELECT p FROM Property p where p.owner.id = ?1")
    List<Property> getAllPropertiesByOwner(Long ownerId);
}
