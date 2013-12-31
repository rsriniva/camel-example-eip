package com.buildria.camel.example.eip.splitter.model;

public class User {

    private final String id;
    
    private final String name;
    
    private final String address;

    public User(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", address=" + address + '}';
    }
    
}
