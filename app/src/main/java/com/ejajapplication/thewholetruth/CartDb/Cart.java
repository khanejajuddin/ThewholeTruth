package com.ejajapplication.thewholetruth.CartDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_image")
    public String productImage;

    @ColumnInfo(name = "product_id")
    public String productId;

    @ColumnInfo(name = "product_Name")
    public String productName;

    @ColumnInfo(name = "product_Price")
    public int productPrice;

    @ColumnInfo(name = "product_quantity")
    public int productQuantity;

    @ColumnInfo(name = "total_price")
    public int totalPrice;

    public Cart(int id, String productImage, String productId, String productName, int productPrice, int productQuantity, int totalPrice) {
        this.id = id;
        this.productImage = productImage;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
