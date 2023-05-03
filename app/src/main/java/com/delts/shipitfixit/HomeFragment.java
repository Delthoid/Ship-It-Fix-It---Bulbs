package com.delts.shipitfixit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delts.shipitfixit.adapter.ShopRecommendationAdapter;
import com.delts.shipitfixit.models.Shop;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<Shop> shops = new ArrayList<>();
        // Inflate the layout for this fragment
        Shop shop = new Shop("Samsung", "Taytay, Rizal", R.drawable.samsung_logo);
        shops.add(shop);
        shop = new Shop("Infinix", "Binangonan, Rizal", R.drawable.infinix_logo);
        shops.add(shop);
        shop = new Shop("OPPO", "Angono, Rizal", R.drawable.oppo_logo);
        shops.add(shop);

        RecyclerView shopRecyclerView = view.findViewById(R.id.shopRecyclerView);
        LinearLayoutManager horizontalLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        shopRecyclerView.setLayoutManager(horizontalLinearLayoutManager);
        ShopRecommendationAdapter adapter = new ShopRecommendationAdapter(shops);
        shopRecyclerView.setAdapter(adapter);

        return view;
    }
}