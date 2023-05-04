package com.delts.shipitfixit.adapter;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.delts.shipitfixit.R;
import com.delts.shipitfixit.TransactionInfoFragment;
import com.delts.shipitfixit.databinding.FragmentTransactionInfoBinding;
import com.delts.shipitfixit.databinding.ShopItemBinding;
import com.delts.shipitfixit.models.Shop;

import java.util.ArrayList;

public class ShopRecommendationAdapter extends RecyclerView.Adapter<ShopRecommendationAdapter.ViewHolder> {
    public ArrayList<Shop> shops;

    public ShopRecommendationAdapter(ArrayList<Shop> shops){
        this.shops = shops;
    }
    public ShopRecommendationAdapter(){}

    public final class ViewHolder extends RecyclerView.ViewHolder{
        //For Shop Recommendation Fragment
        FragmentTransactionInfoBinding ftBinding;
        TextView shopName, shopLocation;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ftBinding = FragmentTransactionInfoBinding.inflate(LayoutInflater.from(itemView.getContext()));
            shopName = (TextView) itemView.findViewById(R.id.shopName);
            shopLocation = (TextView) itemView.findViewById(R.id.shopLocationName);
            imageView = (ImageView) itemView.findViewById(R.id.imageShopRecommendation);
            cardView = (CardView) itemView.findViewById(R.id.shopRecommendationCardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Shop shop = shops.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("name", shop.getName());
                    bundle.putString("location", shop.getLocation());
                    bundle.putInt("image", shop.getImage());

                    TransactionInfoFragment fragment = new TransactionInfoFragment();
                    fragment.setArguments(bundle);

                    NavHostFragment navHostFragment = (NavHostFragment) ((AppCompatActivity) v.getContext())
                            .getSupportFragmentManager().findFragmentById(R.id.navigationController);
                    FragmentTransaction fragmentTransaction = navHostFragment.getChildFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.navigationController, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        public void bind(Shop shop){
            //ShopRecommendationFragment
            shopName.setText(shop.getName());
            shopLocation.setText(shop.getLocation());
            imageView.setImageResource(shop.getImage());
        }
        public TextView getShopName(){
            return shopName;
        }
        public TextView getShopLocation(){
            return shopLocation;
        }
        public ImageView getImageView(){ return imageView; }
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
