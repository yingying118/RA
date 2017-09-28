package com.resouceallocation.service;

import com.resouceallocation.model.Project;
import com.resouceallocation.model.Resource;
import com.resouceallocation.repository.ProjectRepository;
import com.resouceallocation.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by azhang on 19/09/2017.
 */
@Service("projectService")
@Transactional
public class ProjectServiceImp implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Override
    public Project findById(int id) {
        return projectRepository.findOne(id);
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    /*
    * Since the method is running with Transaction, No need to call hibernate update explicitly.
    * Just fetch the entity from db and update it with proper values within transaction.
    * It will be updated in db once transaction ends.
    */
    @Override
    public void updateProject(Project project) {
        Project entity = projectRepository.findOne(project.getProjectId());

        if(entity != null){
            entity.setProjectName(project.getProjectName());
            entity.setResources(project.getResources());
            entity.setDescription(project.getDescription());

        }
    }

    @Override
    public void deleteProjectById(int id) {
        for(Resource resource: resourceRepository.findAll()){
            resource.getProjects().remove(findById(id));
        }
        projectRepository.delete(id);


    }

    @Override
    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public boolean isProjectExist(Project project) {
        return projectRepository.exists(project.getProjectId());
    }
}
