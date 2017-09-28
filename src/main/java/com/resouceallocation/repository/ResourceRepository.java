package com.resouceallocation.repository;
import com.resouceallocation.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by azhang on 18/09/2017.
 */
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
