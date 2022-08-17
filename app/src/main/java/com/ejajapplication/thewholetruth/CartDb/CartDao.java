package com.ejajapplication.thewholetruth.CartDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void addCart(Cart... carts);

    @Query("SELECT EXISTS(SELECT * FROM Cart WHERE product_id = :pid)")
    boolean checkExist(String pid);

    @Query("SELECT * FROM Cart")
    List<Cart> getCart();

    @Query("DELETE FROM Cart WHERE product_id = :pid")
    void deleteById(int pid);

    @Update
    void updateCart(Cart... carts);

    @Query("DELETE FROM Cart")
    void deleteAllCart();
}
