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
@Service("resourceService")
@Transactional
public class ResourceServiceImp implements ResourceService{

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public Resource findById(int id) {
        return resourceRepository.findOne(id);
    }

    @Override
    public void saveResource(Resource resource) {
        resourceRepository.save(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        Resource entity = resourceRepository.findOne(resource.getId());
        if(entity!=null){
            entity.setName(resource.getName());
            entity.setProjects(resource.getProjects());
        }
    }

    @Override
    public void deleteResourceById(int id) {
        for(Project project: projectRepository.findAll() ){
            project.getResources().remove(findById(id));
        }
        resourceRepository.delete(id);

    }

    @Override
    public void deleteAllResources() {
        resourceRepository.deleteAll();
    }

    @Override
    public List<Resource> findAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public boolean isResourceExist(Resource resource) {
        return resourceRepository.exists(resource.getId());
    }
}
