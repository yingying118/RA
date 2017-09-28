package com.resouceallocation.repository;

import com.resouceallocation.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by azhang on 18/09/2017.
 */
@Repository

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
