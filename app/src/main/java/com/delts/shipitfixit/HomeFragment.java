package com.delts.shipitfixit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.delts.shipitfixit.adapter.ShopRecommendationAdapter;
import com.delts.shipitfixit.database.ShopDBHelper;
import com.delts.shipitfixit.databinding.FragmentHomeBinding;
import com.delts.shipitfixit.models.Shop;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    ArrayList<Shop> shops;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //Will retrieve all shop from the database and apply it to the adapter to inflate recycler view
        ShopDBHelper shopDBHelper = new ShopDBHelper(getContext());
        shops = shopDBHelper.retrieveAllShops();

        //Set up of recycler view
        LinearLayoutManager horizontalLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.shopRecyclerView.setLayoutManager(horizontalLinearLayoutManager);
        ShopRecommendationAdapter adapter = new ShopRecommendationAdapter(shops);
        binding.shopRecyclerView.setAdapter(adapter);

        binding.searchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchBox.setIconified(false);
            }
        });

        binding.searchBox.setIconifiedByDefault(false);
        binding.searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                ArrayList<Shop> filteredList= new ShopDBHelper(getContext()).retrieveShopByName(name);
                ShopRecommendationAdapter adapter = new ShopRecommendationAdapter(filteredList);
                binding.shopRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                binding.shopRecommendationLabel.setText(name.isEmpty() ? "Recommended Shops" : "Results for '" + name + "'");
                return true;
            }
        });

        return binding.getRoot();
    }

    public void filterItems(String name){

    }
}