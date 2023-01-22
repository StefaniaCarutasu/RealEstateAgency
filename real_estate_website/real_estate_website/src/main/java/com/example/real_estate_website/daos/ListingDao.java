package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingDao extends JpaRepository<Listing, Long> {

    @Query(value = "SELECT l FROM Listing l")
    List<Listing> getAllListings();

    @Query(value = "SELECT l FROM Listing l where l.id = ?1")
    Listing getListingById(Long listingId);
}
