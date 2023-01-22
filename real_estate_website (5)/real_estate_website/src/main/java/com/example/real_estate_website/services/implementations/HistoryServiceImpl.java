package com.example.real_estate_website.services.implementations;

import com.example.real_estate_website.models.JP_AUDIT;
import com.example.real_estate_website.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    @Override
    public void logAction(JP_AUDIT JPAUDIT) {

    }
}
