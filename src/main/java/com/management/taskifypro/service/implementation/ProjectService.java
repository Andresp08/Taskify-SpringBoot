package com.management.taskifypro.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.taskifypro.model.entity.Project;
import com.management.taskifypro.model.response.ApiResponse;
import com.management.taskifypro.repository.ProjectRespository;
import com.management.taskifypro.service.interfaces.IProjectService;
import com.management.taskifypro.util.builder.ResponseBuilder;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectRespository projectRespository;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Override
    public ResponseEntity<ApiResponse> findAll() {
        List<Project> projects = this.projectRespository.findAll();
        return responseBuilder.buildResponse(HttpStatus.OK.value(), "List of projects", projects);
    }
    
}
