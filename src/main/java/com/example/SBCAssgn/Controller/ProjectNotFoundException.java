package com.example.SBCAssgn.Controller;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String msg){
        super(msg);
    }
}
