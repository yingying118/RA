package com.resouceallocation.controller;

import com.resouceallocation.model.Project;
import com.resouceallocation.model.Resource;
import com.resouceallocation.service.ProjectService;
import com.resouceallocation.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by azhang on 19/09/2017.
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
    //service which will do all data retrieval/manipulate work
    @Autowired
    ProjectService projectService;
    @Autowired
    ResourceService resourceService;
    @RequestMapping(value="/createproject", method = RequestMethod.POST)
    public Project createProject(@RequestBody Project project)  {

        projectService.saveProject(project);

        return project;
    }

    @RequestMapping(value="/getallprojects", method = RequestMethod.GET)
    public List<Project> getAllProjects(){
        List<Project> lst = projectService.findAllProjects();
        return lst;
    }
    @RequestMapping(value="/removeproject/{id}", method=RequestMethod.DELETE)
    public void removeProject(@PathVariable("id") int id){
        projectService.deleteProjectById(id);

    }
    //=============================================  API for resource ==========================================
    //create
    @RequestMapping(value="/createresource", method = RequestMethod.POST)
    public Resource createResource(@RequestBody Resource resource) {
        resourceService.saveResource(resource);
        return resource;
    }
    //read
    @RequestMapping(value="/getallresources", method = RequestMethod.GET)
    public List<Resource> getAllResources(){
        List<Resource> lst = resourceService.findAllResources();
        return lst;
    }
    @RequestMapping(value="/removeresource/{id}", method=RequestMethod.DELETE)
    public void removeResource(@PathVariable("id") int id){
        resourceService.deleteResourceById(id);
    }
    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public Resource updateResource(@PathVariable("id") int id, @RequestBody Resource resource){
        Resource currentResource = resourceService.findById(id);
        currentResource.setName(resource.getName());
        resourceService.updateResource(currentResource);
        return currentResource;
    }
}
