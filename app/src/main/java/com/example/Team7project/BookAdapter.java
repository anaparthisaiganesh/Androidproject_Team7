package com.example.Team7project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ExampleViewHolder> {
    private ArrayList<BookItem> mExampleList;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mItemName;
        public TextView mItemDescription;
        public TextView mItemPrice;
        public TextView mItemDate;

        public ImageView mImage;

        public ExampleViewHolder(View itemView) {
            super(itemView);

            mItemName=itemView.findViewById(R.id.item_name);
            mItemDescription=itemView.findViewById(R.id.item_description);
            mItemPrice = itemView.findViewById(R.id.item_price);
            mItemDate = itemView.findViewById(R.id.item_date);
            mImage=itemView.findViewById(R.id.imageView);
        }
    }

    public BookAdapter(ArrayList<BookItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        BookItem currentItem = mExampleList.get(position);
        holder.mItemName.setText(currentItem.getItemName());
        holder.mItemDescription.setText(currentItem.getItemDescription());
        holder.mItemPrice.setText(currentItem.getItemPrice());
        holder.mItemDate.setText(currentItem.getItemDate());
        holder.mImage.setImageResource(currentItem.getImgaeId());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
