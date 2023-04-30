package com.delts.shipitfixit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delts.shipitfixit.R;
import com.delts.shipitfixit.databinding.ShopItemBinding;
import com.delts.shipitfixit.models.Shop;

import java.util.ArrayList;

public class ShopRecommendationAdapter extends RecyclerView.Adapter<ShopRecommendationAdapter.ViewHolder> {

    private ShopItemBinding binding;

    private ArrayList<Shop> shops = new ArrayList<>();

    public ShopRecommendationAdapter(){
        shops.add(new Shop("Samsung", "Taytay, Rizal"));
        shops.add(new Shop("Oppo", "Binangonan, Rizal"));
        shops.add(new Shop("Infinix", "Angono, Rizal"));
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        TextView shopName, shopLocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopName = (TextView) itemView.findViewById(R.id.shopName);
            shopLocation = (TextView) itemView.findViewById(R.id.shopLocationName);
        }
        public void bind(Shop shop){
            shopName.setText(shop.getName());
            shopLocation.setText(shop.getLocation());
        }
        public TextView getShopName(){
            return shopName;
        }
        public TextView getShopLocation(){
            return shopLocation;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecommendationAdapter.ViewHolder holder, int position) {
        Shop shop = shops.get(position);
        holder.bind(shop);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}
