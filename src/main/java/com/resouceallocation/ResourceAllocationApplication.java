package com.resouceallocation;

import com.resouceallocation.model.Project;
import com.resouceallocation.model.Resource;
import com.resouceallocation.repository.ProjectRepository;
import com.resouceallocation.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ResourceAllocationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ResourceAllocationApplication.class, args);
	}

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ResourceRepository resourceRepository;

	@Transactional
	@Override
	public void run(String... strings) throws Exception {
		Project payX = new Project(01, "PayX");
		//Project scd = new Project(02, "SCD");

		Resource ariel = new Resource("Ariel",001);
		Resource jack = new Resource("Jack",002);

		Set<Project> projects = new HashSet<Project>();
		projects.add(payX);
		//projects.add(scd);

		jack.setProjects(projects);
		ariel.setProjects(projects);

		resourceRepository.save(jack);
		resourceRepository.save(ariel);

		Set<Resource> resources = new HashSet<Resource>();
		resources.add(jack);
		resources.add(ariel);

		payX.setResources(resources);
		//scd.setResources(resources);

		projectRepository.save(payX);
		//projectRepository.save(scd);


		List<Resource> resourcesList=resourceRepository.findAll();
		List<Project> projectList = projectRepository.findAll();

		System.out.println(resourcesList.size());
		System.out.println(projectList.size());

		System.out.println("===================resources info:==================");
		resourcesList.forEach(resource->System.out.println(resource.toString()));

		System.out.println("===================project info:==================");
		projectList.forEach(project->System.out.println(project.toString()));

		Project scd = new Project(02, "SCD");
		projects.add(scd);
		scd.setResources(resources);
		projectRepository.save(scd);
		projectList = projectRepository.findAll();
		System.out.println("===================project info:==================");
		projectList.forEach(project->System.out.println(project.toString()));
	}
}
