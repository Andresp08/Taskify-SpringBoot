package com.management.taskifypro.service.interfaces.projectinterfaces;

import org.springframework.http.ResponseEntity;

import com.management.taskifypro.model.response.ApiResponse;

public interface IProjectService {
    public ResponseEntity<ApiResponse> findAll();
}
