package com.delts.shipitfixit.models;

public class Shop {
    String name;
    String location;

    public Shop (String name, String location){
        this.name = name;
        this.location = location;
    }

    public Shop(){}

    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }

}
