package com.example.real_estate_website.services;

import com.example.real_estate_website.models.JP_AUDIT;

public interface HistoryService {

    void logAction(JP_AUDIT JPAUDIT);
}
