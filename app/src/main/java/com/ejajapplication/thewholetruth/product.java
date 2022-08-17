package com.ejajapplication.thewholetruth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class product extends AppCompatActivity {
    String menuId;
    RecyclerView recyclerView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tv = findViewById(R.id.Menu_name);
        tv.setText(getIntent().getStringExtra("menuName"));
        menuId = getIntent().getStringExtra("menuID");
        getSupportActionBar().setTitle(tv.getText().toString());

        recyclerView = findViewById(R.id.RecView_product);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        get_product();
    }
    void get_product(){
        Call<List<getProduct>> call = controller.getInstance().getApi().getProduct(menuId);
        call.enqueue(new Callback<List<getProduct>>() {
            @Override
            public void onResponse(Call<List<getProduct>> call, Response<List<getProduct>> response) {
                List<getProduct> data = response.body();
                productAdapter adapter = new productAdapter(data,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<getProduct>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}