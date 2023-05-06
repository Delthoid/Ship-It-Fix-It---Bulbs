package com.delts.shipitfixit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.delts.shipitfixit.adapter.ShopServicesAdapter;
import com.delts.shipitfixit.database.ShopDBHelper;
import com.delts.shipitfixit.database.ShopServicesDBHelper;
import com.delts.shipitfixit.databinding.ActivityViewStoreBinding;
import com.delts.shipitfixit.databinding.FragmentTransactionInfoBinding;
import com.delts.shipitfixit.models.Shop;
import com.delts.shipitfixit.models.ShopService;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewStoreActivity extends AppCompatActivity {

    private ActivityViewStoreBinding binding;
    private FragmentTransactionInfoBinding infoBinding;

    private Shop shop;
    private HashMap<Integer, ShopService> services = new HashMap<>();
    private ShopDBHelper shopDBHelper;
    private ShopServicesDBHelper shopServicesDBHelper;
    private ShopServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewStoreBinding.inflate(getLayoutInflater());
        infoBinding = FragmentTransactionInfoBinding.bind(binding.mainLinearLayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        shopDBHelper = new ShopDBHelper(getApplicationContext());
        shopServicesDBHelper = new ShopServicesDBHelper(getApplicationContext());


        shop = shopDBHelper.retrieveShopByName(getIntent().getStringExtra("shopName")).get(0);
        services = shopServicesDBHelper.shopServicesByShopId(shop.getId());
        servicesAdapter = new ShopServicesAdapter(services);

        infoBinding.shopNameTransactionInfo.setText(shop.getName());
        infoBinding.shopLocationTransactionInfo.setText(shop.getLocation());
        infoBinding.shopImage.setImageResource(shop.getImage());

        infoBinding.shopServicesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        infoBinding.shopServicesRecyclerView.setAdapter(new ShopServicesAdapter(services));

        infoBinding.servicesViewToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicesAdapter.toggleViewMode();
                if (servicesAdapter.getViewMode()) {
                    infoBinding.shopServicesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    infoBinding.shopServicesRecyclerView.setAdapter(new ShopServicesAdapter(services));
                    infoBinding.servicesViewToggle.setImageDrawable(getResources().getDrawable(R.drawable.baseline_grid_view_24));
                } else {
                    infoBinding.shopServicesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    infoBinding.shopServicesRecyclerView.setAdapter(new ShopServicesAdapter(services));
                    infoBinding.servicesViewToggle.setImageDrawable(getResources().getDrawable(R.drawable.baseline_view_list_24));
                }
            }
        });

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