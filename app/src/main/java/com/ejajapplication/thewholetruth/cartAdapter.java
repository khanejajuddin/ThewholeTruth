package com.ejajapplication.thewholetruth;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.ejajapplication.thewholetruth.CartDb.Cart;
import com.ejajapplication.thewholetruth.CartDb.CartDao;
import com.ejajapplication.thewholetruth.CartDb.CartDataBase;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder>{
    List<Cart> carts;
    TextView total_cart_price;

    public cartAdapter(List<Cart> carts, TextView total_cart_price) {
        this.carts = carts;
        this.total_cart_price = total_cart_price;
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecart_layout,parent,false);
        return new cartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Cart_Name.setText(carts.get(position).getProductName());
        holder.Total_Cost.setText(String.valueOf(carts.get(position).getTotalPrice()));
        holder.price_Cart.setText(String.valueOf(carts.get(position).getProductPrice()));
        holder.txt_count_Cart.setRange(1,20);
        holder.txt_count_Cart.setNumber(String.valueOf(carts.get(position).getProductQuantity()));
        Glide.with(holder.Cart_Name.getContext()).load("http://192.168.1.106/thewholetruth/image/"+carts.get(position).getProductImage()).into(holder.image_Cart);
        holder.txt_count_Cart.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qnt = Integer.parseInt(holder.txt_count_Cart.getNumber());
                int price  = carts.get(position).getProductPrice();
                int total = price*qnt;
                CartDataBase db = CartDataBase.getInstance(holder.Cart_Name.getContext());
                CartDao cartDao = db.cartDao();
                Cart cart = new Cart();
                cart.setId(carts.get(position).getId());
                cart.setTotalPrice(total);
                cart.setProductQuantity(qnt);
                cart.setProductPrice(carts.get(position).getProductPrice());
                cart.setProductImage(carts.get(position).getProductImage());
                cart.setProductId(carts.get(position).getProductId());
                cart.setProductName(holder.Cart_Name.getText().toString());
                db.cartDao().updateCart(cart);
                carts.get(position).setTotalPrice(total);
                int sum = 0,i;
                for (i=0;i<carts.size();i++){
                    sum = sum+(carts.get(i).getTotalPrice());
                }
                total_cart_price.setText(String.valueOf(sum));
                carts.get(position).setTotalPrice(total);
                carts.get(position).setProductQuantity(qnt);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class cartViewHolder extends RecyclerView.ViewHolder{
        ImageView image_Cart;
        TextView Cart_Name,price_Cart,Total_Cost;
        ElegantNumberButton txt_count_Cart;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Cart = itemView.findViewById(R.id.image_cart);
            Cart_Name = itemView.findViewById(R.id.CartName);
            price_Cart = itemView.findViewById(R.id.price_cart);
            Total_Cost = itemView.findViewById(R.id.TotalCost);
            txt_count_Cart = itemView.findViewById(R.id.txt_count_cart);
        }
    }
}
