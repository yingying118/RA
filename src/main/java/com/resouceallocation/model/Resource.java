package com.resouceallocation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by azhang on 18/09/2017.
 */
@Entity

public class Resource implements Serializable {
    @Id
    private int id;
    private String name;



    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "resource_project", joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "PROJECT_ID"))
    private Set<Project> projects;
    @JsonIgnore
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    public Resource(){

    }
    public Resource(String name, int id){
        this.id = id;
        this.name = name;
    }
    public Resource(String name, int id, Set<Project> projects){
        this.name = name;
        this.id = id;
        this.projects= projects;
    }


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();

        try {
            jsonInfo.put("id",this.id);
            jsonInfo.put("name",this.name);

            info = jsonInfo.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**
        JSONArray subArray = new JSONArray();
        this.projects.forEach(sub->{
            JSONObject subJson = new JSONObject();
            try {
                subJson.put("name", sub.getProjectName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            subArray.put(subJson);
        });
        jsonInfo.put("projects", subArray);
        info = jsonInfo.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }**/
        return info;

}
}
