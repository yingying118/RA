package com.resouceallocation.service;


import com.resouceallocation.model.Resource;

import java.util.List;

/**
 * Created by azhang on 19/09/2017.
 */
public interface ResourceService {
    Resource findById(int id);
    void saveResource(Resource resource);
    void updateResource(Resource resource);
    void deleteResourceById(int id);
    void deleteAllResources();
    List<Resource> findAllResources();
    boolean isResourceExist(Resource resource);
}
