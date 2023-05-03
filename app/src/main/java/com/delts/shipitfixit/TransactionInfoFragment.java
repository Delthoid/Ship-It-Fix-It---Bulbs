package com.delts.shipitfixit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delts.shipitfixit.adapter.ShopRecommendationAdapter;
import com.delts.shipitfixit.databinding.FragmentTransactionInfoBinding;

public class TransactionInfoFragment extends Fragment {
    FragmentTransactionInfoBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionInfoBinding.inflate(inflater, container,false);
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        String location = bundle.getString("location");
        int image = bundle.getInt("image");

        binding.shopNameTransactionInfo.setText(name);
        binding.shopLocationTransactionInfo.setText(location);

        return binding.getRoot();
    }
}