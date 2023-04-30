package com.delts.shipitfixit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delts.shipitfixit.adapter.ShopRecommendationAdapter;

public class HomeFragment extends Fragment {
    private RecyclerView shopRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        shopRecyclerView = view.findViewById(R.id.shopRecyclerView);
        shopRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ShopRecommendationAdapter adapter = new ShopRecommendationAdapter();
        shopRecyclerView.setAdapter(adapter);

        return view;
    }
}