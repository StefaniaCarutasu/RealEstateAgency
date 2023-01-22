package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {

}
