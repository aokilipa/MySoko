package com.antony.myshop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.antony.myshop.R;
import com.antony.myshop.data.model.Product;
import com.antony.myshop.ui.product.ShowProductDetailActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mProducts;
    Context mContext;

    @Inject
    public ProductAdapter() {
        mProducts = new ArrayList<>();
    }

    public void setRibots(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myshop, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {

        Product product = mProducts.get(position);

        holder.tvTitle.setText(product.getThumbnail());
        holder.tvDesc.setText(product.getDescription());
        holder.tvPrice.setText(Double.toString(product.getPrice()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ShowProductDetailActivity.class);

                //intent.putExtra()

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivCardImage) ImageView thumbnail;
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_desc) TextView tvDesc;
        @BindView(R.id.tv_price) TextView tvPrice;
        @BindView(R.id.card_view)
        CardView cardView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
