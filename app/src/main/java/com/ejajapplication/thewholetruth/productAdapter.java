package com.ejajapplication.thewholetruth;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.media.Image;
import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.productViewHolder>{
    List<getProduct> data;
    Context context;

    public productAdapter(List<getProduct> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_prouduct,parent,false);
        return new productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder,int position) {
        getProduct getProduct = data.get(position);
        holder.txt_product_name.setText(data.get(position).getName());
        holder.product_price.setText(data.get(position).getPrice());
        Glide.with(holder.txt_product_name.getContext()).load("http://192.168.1.106/thewholetruth/image/"+data.get(position).getImage()).into(holder.product_img);

        holder.Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Add_to_cart_activity.class);
                intent.putExtra("product_name",getProduct.getName());
                intent.putExtra("product_Price",getProduct.getPrice());
                intent.putExtra("product_Image",getProduct.getImage());
                intent.putExtra("product_Id",getProduct.getId());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class productViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_price;
        TextView txt_product_name;
        Button Add_to_cart;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img = (ImageView) itemView.findViewById(R.id.image_product);
            product_price = (TextView) itemView.findViewById(R.id.product_price);
            txt_product_name = (TextView) itemView.findViewById(R.id.tct_product_name);
            Add_to_cart = (Button) itemView.findViewById(R.id.Add_to_cart);

        }
    }
}
