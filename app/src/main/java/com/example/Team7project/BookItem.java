package com.example.Team7project;

public class BookItem {
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private String itemDate;

    private int imgaeId;

    public BookItem(String itemName, String itemDescription, String itemPrice, String itemDate,int imgid) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemDate = itemDate;
        imgaeId=imgid;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemDate() {
        return itemDate;
    }

    public int getImgaeId() {
        return imgaeId;
    }
}
