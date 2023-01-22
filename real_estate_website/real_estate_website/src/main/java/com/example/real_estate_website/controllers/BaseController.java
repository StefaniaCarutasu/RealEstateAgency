package com.example.real_estate_website.controllers;

import com.example.real_estate_website.errors.RequestError;
import com.example.real_estate_website.models.JP_AUDIT;
import com.example.real_estate_website.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected final String DEFAULT_ERROR_MESSAGE = "An error occurred. Please try later.";
    protected final String GENERIC_BAD_REQUEST_MESSAGE = "The resource you asked for is not available. Try something else.";

    @Autowired
    private HistoryService historyService;

    protected RequestError createErrorResponse(Integer errorCode, String customMessage, String errorMessage) {
        return new RequestError(errorCode, customMessage, errorMessage);
    }

    protected boolean logAction(String action, String originPoint) {
        try {
            JP_AUDIT JPAUDIT = new JP_AUDIT();
//            JPAUDIT.setAction(action);
//            Date date = new Date();
//            JPAUDIT.setDate(new java.sql.Date(date.getTime()));
//            JPAUDIT.setOriginPoint(originPoint);
//            historyService.logAction(JPAUDIT);
            return true;
        } catch (Exception e) {
            return  false;
        }
    }

    protected Map<Integer, String> createSuccessResponse(String message) {
        Map<Integer, String> successResponse = new HashMap<>();
        successResponse.put(200, message);
        return successResponse;
    }
}
