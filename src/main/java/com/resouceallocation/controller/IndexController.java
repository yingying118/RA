package com.resouceallocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by azhang on 18/09/2017.
 */
@Controller

public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/overview")
    public String overview(){
        return "overview";
    }
    @GetMapping("/projects")
    public String projects(){
        return "projects";
    }
    @GetMapping("/resources")
    public String resources(){
        return "resources";
    }
    @GetMapping("/multiSelect")
    public String multiSelect(){
        return "multiSelect";
    }

}
