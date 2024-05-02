package org.example;


public class NameGetter {

    private String name;

    public void setName(String name){
        this.name=name;
    }

    public String getGreeting(){
        return "Hello "+name+"!";
    }
}
