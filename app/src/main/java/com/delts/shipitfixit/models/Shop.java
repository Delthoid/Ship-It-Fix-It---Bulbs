package com.delts.shipitfixit.models;

public class Shop {
    String name;
    String location;
    int image;

    public Shop (String name, String location, int image){
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public Shop(){}

    public void setName(String name){this.name = name;}
    public void setLocation(String location){this.location = location;}
    public void setImage(int image){this.image = image;}
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public int getImage(){return image;}
}
