package com.management.taskifypro.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.taskifypro.model.enums.TaskStatus;
import com.management.taskifypro.util.constants.GeneralConstants;

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskTitle;

    @Column(nullable = false)
    private String taskDescription;

    @Column(nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern = GeneralConstants.DATE_FORMAT, timezone = "America/Bogota")
    private LocalDate taskDueDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "task_manager_id")
    private User taskManager;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
