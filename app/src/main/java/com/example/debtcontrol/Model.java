package com.example.debtcontrol;

public class Model {
    private  String id;
    private String name;
    private String itemList;
    private String total;



    public Model(String id, String name, String itemList, String total) {
        this.id = id;
        this.name = name;
        this.itemList = itemList;
        this.total = total;
    }

    public Model() {

    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getItemList() {
        return itemList;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTotal() {
        return total;
    }
}
