package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.ReviewDao;
import com.example.real_estate_website.models.Review;
import com.example.real_estate_website.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    public boolean addReview(Review review) {
        try {
            reviewDao.save(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
