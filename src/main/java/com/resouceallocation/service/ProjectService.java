package com.resouceallocation.service;

import com.resouceallocation.model.Project;

import java.util.List;

/**
 * Created by azhang on 19/09/2017.
 */
public interface ProjectService {
    Project findById(int id);
    void saveProject(Project project);
    void updateProject(Project project);
    void deleteProjectById(int id);
    void deleteAllProjects();
    List<Project> findAllProjects();
    boolean isProjectExist(Project project);
}
