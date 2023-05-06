package com.delts.shipitfixit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.delts.shipitfixit.R;
import com.delts.shipitfixit.models.ShopService;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopServicesAdapter extends RecyclerView.Adapter<ShopServicesAdapter.ViewHolder> {

    private ArrayList<ShopService> services = new ArrayList<>();
    private boolean isGridView = true;
    public ShopServicesAdapter(HashMap<Integer, ShopService> services) {
        this.services.clear();
        for (ShopService service : services.values()) {
            this.services.add(service);
        }
    }

    public void toggleViewMode() {
        isGridView = !isGridView;
    }

    public boolean getViewMode() {
        return isGridView;
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameLabel, priceLabel, descriptionLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.serviceItemCardView);
            nameLabel = (TextView) itemView.findViewById(R.id.serviceItemName);
            priceLabel = (TextView) itemView.findViewById(R.id.serviceItemPrice);
            descriptionLabel = (TextView) itemView.findViewById(R.id.serviceItemDescription);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void bind(ShopService service) {
            nameLabel.setText(service.getServiceName());
            priceLabel.setText("₱" + service.getEstimatedPrice());
            descriptionLabel.setText(service.getServiceDescription());
        }
    }

    @NonNull
    @Override
    public ShopServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_service_item, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopServicesAdapter.ViewHolder holder, int position) {
        ShopService service = services.get(position);
        holder.bind(service);

    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}
