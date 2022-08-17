package com.ejajapplication.thewholetruth;

public class getProduct {

    public String id;
    public String name;
    public String image;
    public String Price;
    public String MenuId;

    public getProduct() {
    }

    public getProduct(String id, String name, String image, String price, String menuId) {
        this.id = id;
        this.name = name;
        this.image = image;
        Price = price;
        MenuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
