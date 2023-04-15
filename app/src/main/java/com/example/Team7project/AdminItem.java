package com.example.Team7project;

public class AdminItem {
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private String itemDate;

    private String imageUrl,onwerUid,uid;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AdminItem(String itemName, String itemDescription, String itemPrice, String itemDate, String imageUrl, String ownerKey,String key) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemDate = itemDate;
        this.imageUrl=imageUrl;
        onwerUid=ownerKey;
        uid=key;
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

    public String getOnwerUid() {
        return onwerUid;
    }

    public String getUid() {
        return uid;
    }
}
