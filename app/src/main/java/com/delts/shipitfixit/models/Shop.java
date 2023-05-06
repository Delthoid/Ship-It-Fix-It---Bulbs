package com.delts.shipitfixit.models;

import java.util.HashMap;

public class Shop {
    int id;
    String name;
    String location;
    int image;
    HashMap<Integer, ShopService> shopServices;
    public Shop (int id, String name, String location, int image){
        this.id = id;
        this.name = name;
        this.location = location;
        this.image = image;
        this.shopServices = new HashMap<>();
    }

    public Shop(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){this.name = name;}
    public void setLocation(String location){this.location = location;}
    public void setImage(int image){this.image = image;}

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public int getImage(){return image;}

    public HashMap<Integer, ShopService> getShopServices() {
        return shopServices;
    }

    public void setShopServices(HashMap<Integer, ShopService> shopServices) {
        this.shopServices = shopServices;
    }
}
