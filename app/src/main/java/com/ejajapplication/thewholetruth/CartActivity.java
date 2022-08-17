package com.ejajapplication.thewholetruth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ejajapplication.thewholetruth.CartDb.Cart;
import com.ejajapplication.thewholetruth.CartDb.CartDao;
import com.ejajapplication.thewholetruth.CartDb.CartDataBase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    String url = "http://192.168.1.106/thewholetruth/order.php";
    String User_id,User_Name,Phone;
    Button orderNowBtn;
    EditText address;
    TextView total_cart_price;
    RecyclerView cartRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        getSupportActionBar().setTitle("Cart");
        getCartData();
        address = findViewById(R.id.order_address);
        orderNowBtn = findViewById(R.id.orderNowBtn);
        orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = total_cart_price.getText().toString();
                String address_str = address.getText().toString();
                if (!amount.equals("0") && !address_str.equals("")) {
                    CartDataBase db = CartDataBase.getInstance(getApplicationContext());
                    CartDao cartDao = db.cartDao();
                    List<Cart> carts = cartDao.getCart();
                    String str = "";
                    for (Cart cart : carts) {
                        str = str + cart.getProductName() + " (x" + cart.getProductQuantity() + ")" + "\t";
                    }
                    final String str1 = str;
                    SharedPreferences sp = getSharedPreferences("login_status", MODE_PRIVATE);
                    User_id = sp.getString("ID", "");
                    User_Name = sp.getString("UserName", "");
                    Phone = sp.getString("Phone", "");
                    OrderNow(User_id, User_Name, str1, Phone, address.getText().toString(), total_cart_price.getText().toString());
                }
                else {
                    Toast.makeText(CartActivity.this, "please fill the detail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void OrderNow(String user_id, String user_name, String order_detail, String phone, String address, String total_amount) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                CartDataBase db = CartDataBase.getInstance(getApplicationContext());
                CartDao cartDao = db.cartDao();
                cartDao.deleteAllCart();
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CartActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("User_id",user_id);
                params.put("Username",user_name);
                params.put("orderDetail",order_detail);
                params.put("Phone",phone);
                params.put("Address",address);
                params.put("Amount",total_amount);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void getCartData() {
        CartDataBase db = CartDataBase.getInstance(this.getApplicationContext());
        CartDao cartDao = db.cartDao();
        cartRecView = findViewById(R.id.recViewCart);
        total_cart_price = findViewById(R.id.total_cart_price);
        cartRecView.setLayoutManager(new LinearLayoutManager(this));

        List<Cart> carts = cartDao.getCart();
        cartAdapter adapter = new cartAdapter(carts,total_cart_price);
        cartRecView.setAdapter(adapter);

        int sum = 0,i;
        for (i=0;i<carts.size();i++){
            sum = sum+(carts.get(i).getTotalPrice());
        }
        total_cart_price.setText(String.valueOf(sum));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                CartDataBase db = CartDataBase.getInstance(getApplicationContext());
                CartDao cartDao = db.cartDao();
                cartDao.deleteById(Integer.parseInt(carts.get(viewHolder.getAdapterPosition()).getProductId()));
                carts.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                int sum = 0,i;
                for (i=0;i<carts.size();i++){
                    sum = sum+(carts.get(i).getTotalPrice());
                }
                total_cart_price.setText(String.valueOf(sum));

            }
        }).attachToRecyclerView(cartRecView);
    }
}