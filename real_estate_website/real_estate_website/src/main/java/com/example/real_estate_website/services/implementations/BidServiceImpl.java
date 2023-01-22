package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.daos.BidDao;
import com.example.real_estate_website.models.Bid;
import com.example.real_estate_website.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    BidDao bidDao;

    @Override
    public boolean addBid(Bid bid) {
        try {
            bidDao.save(bid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
