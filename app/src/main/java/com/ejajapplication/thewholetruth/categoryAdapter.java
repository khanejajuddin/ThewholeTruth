package com.ejajapplication.thewholetruth;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder> {
    List<category> categories;
    Context context;

    public categoryAdapter(List<category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_layout,parent,false);
        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        holder.Name_category.setText(categories.get(position).getName());
        Glide.with(holder.Name_category.getContext()).load("http://192.168.1.106/thewholetruth/menu image/"+categories.get(position).getImage()).into(holder.img_category);
        String menuID = categories.get(position).getID();
        String menuName = categories.get(position).getName();
        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,product.class);
                intent.putExtra("menuID",menuID);
                intent.putExtra("menuName",menuName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class categoryViewHolder extends RecyclerView.ViewHolder{
        ImageView img_category;
        TextView Name_category;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = (ImageView) itemView.findViewById(R.id.image_product);
            Name_category = (TextView) itemView.findViewById(R.id.txt_menu_name);

        }
    }
}
