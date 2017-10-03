package com.resouceallocation.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
public class Project implements Serializable {
    @Id
    @Column(name = "PROJECT_ID")
    private int projectId;
    private String projectName;
    private String description;

    public Project(){}
    public Project( int id, String name){
        this.projectName = name;
        this.projectId = id;
    }
    public Project( int id, String name,String description){
        this.projectName = name;
        this.projectId = id;
        this.description = description;
    }
    public Project (int id, String projectName, Set<Resource> resources){
        this.projectName = projectName;
        this.resources = resources;
        this.projectId = id;
    }


    @ManyToMany(mappedBy="projects", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Resource> resources;

    public Set<Resource> getResources() {
        return resources;
    }
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }




    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        try {
            jsonInfo.put("name",this.projectName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray studentArray = new JSONArray();
        if(this.resources != null && resources.size() > 0){
            this.resources.forEach(resource->{
                JSONObject subJson = new JSONObject();
                try {
                    subJson.put("id", resource.getId());

                    subJson.put("name", resource.getName());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                studentArray.put(subJson);
            });
        }
        try {
            jsonInfo.put("resources", studentArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        info = jsonInfo.toString();
        return info;
    }


}
