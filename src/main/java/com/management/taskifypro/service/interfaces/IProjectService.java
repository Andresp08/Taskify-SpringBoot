package com.management.taskifypro.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.management.taskifypro.model.response.ApiResponse;

public interface IProjectService {
    public ResponseEntity<ApiResponse> findAll();
}
