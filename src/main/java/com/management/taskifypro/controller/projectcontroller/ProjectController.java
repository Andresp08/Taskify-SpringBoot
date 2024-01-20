package com.management.taskifypro.controller.projectcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.taskifypro.model.response.ApiResponse;
import com.management.taskifypro.service.interfaces.projectinterfaces.IProjectService;

// @RestController
// @RequestMapping("/api/v1/projects")
// public class ProjectController {

//     @Autowired
//     private IProjectService projectService;

//     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//     public ResponseEntity<ApiResponse> getProjects() {
//         return projectService.findAll();
//     }
// }
