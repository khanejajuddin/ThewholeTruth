package com.ejajapplication.thewholetruth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ejajapplication.thewholetruth.CartDb.Cart;
import com.ejajapplication.thewholetruth.CartDb.CartDao;
import com.ejajapplication.thewholetruth.CartDb.CartDataBase;

public class Add_to_cart_activity extends AppCompatActivity {
    int price;
    TextView product_Name,Product_Price,Total_amount,final_product;
    EditText comment;
    ElegantNumberButton txt_count;
    ImageView img_cart_product;
    Button add_to_Cart;
    String Product_name,Product_price,image,product_Id;
    int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        Product_name = getIntent().getStringExtra("product_name");
        Product_price = getIntent().getStringExtra("product_Price");
        image = getIntent().getStringExtra("product_Image");
        product_Id = getIntent().getStringExtra("product_Id");

        txt_count = findViewById(R.id.txt_count);
        product_Name = findViewById(R.id.txt_cart);
        Product_Price = findViewById(R.id.price_product_cart);
        final_product = findViewById(R.id.name_product_final_cart);
        comment = findViewById(R.id.comment);
        Total_amount = findViewById(R.id.Total_price);
        img_cart_product = findViewById(R.id.img_cart_product);
        add_to_Cart = findViewById(R.id.Add_to_cart_cart);

        product_Name.setText(Product_name);
        Product_Price.setText(Product_price + " Rs");
        final_product.setText(Product_name);
        Glide.with(getApplicationContext()).load("http://192.168.1.106/thewholetruth/image/" + image).into(img_cart_product);

        price = Integer.parseInt(Product_price);
        Total_amount.setText(Product_price);

        txt_count.setRange(1,20);

        txt_count.setNumber("1");
        txt_count.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = txt_count.getNumber();
                int quantity1 = Integer.parseInt(quantity);
                total = quantity1 * price;
                String amount = String.valueOf(total);
                Total_amount.setText(amount);
            }
        });

        add_to_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedToCart();
            }
        });
    }

    private void savedToCart() {
        CartDataBase db = CartDataBase.getInstance(this.getApplicationContext());
        CartDao cartDao = db.cartDao();
        Boolean check = cartDao.checkExist(product_Id);
        if (check == false){
            Cart cart = new Cart();
            cart.productId = product_Id;
            cart.productImage = image;
            cart.productName = Product_name;
            int price = Integer.parseInt(Product_price);
            cart.productPrice = price;
            cart.productQuantity  = Integer.parseInt(txt_count.getNumber());
            cart.totalPrice  = Integer.parseInt(Total_amount.getText().toString());
            db.cartDao().addCart(cart);
            Toast.makeText(getApplicationContext(),"order inserted into the cart",Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(getApplicationContext(),CartActivity.class));
        }
    }

}