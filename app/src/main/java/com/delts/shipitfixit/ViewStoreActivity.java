package com.delts.shipitfixit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.delts.shipitfixit.database.ShopDBHelper;
import com.delts.shipitfixit.databinding.ActivityViewStoreBinding;
import com.delts.shipitfixit.databinding.FragmentTransactionInfoBinding;
import com.delts.shipitfixit.models.Shop;

public class ViewStoreActivity extends AppCompatActivity {

    private ActivityViewStoreBinding binding;
    private FragmentTransactionInfoBinding infoBinding;

    private Shop shop;
    private ShopDBHelper shopDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewStoreBinding.inflate(getLayoutInflater());
        infoBinding = FragmentTransactionInfoBinding.bind(binding.mainLinearLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        shopDBHelper = new ShopDBHelper(getApplicationContext());
        shop = shopDBHelper.retrieveShopByName(getIntent().getStringExtra("shopName")).get(0);

        infoBinding.shopNameTransactionInfo.setText(shop.getName());
        infoBinding.shopLocationTransactionInfo.setText(shop.getLocation());
        infoBinding.shopImage.setImageResource(shop.getImage());

        setContentView(binding.getRoot());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}