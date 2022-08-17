package com.ejajapplication.thewholetruth.CartDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Cart.class},version = 1,exportSchema = false)
public abstract class CartDataBase extends RoomDatabase {
    public abstract CartDao cartDao();

    private static CartDataBase INSTANCE;

    public static CartDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CartDataBase.class, "CART").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
