package com.management.taskifypro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.taskifypro.model.entity.Project;

public interface ProjectRespository extends JpaRepository<Project, Long>{
    
}
