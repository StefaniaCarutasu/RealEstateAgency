package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidDao extends JpaRepository<Bid, Long> {
}
