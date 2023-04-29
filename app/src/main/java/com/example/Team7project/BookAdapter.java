package com.example.Team7project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ExampleViewHolder> {


    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    private ArrayList<BookItem> mExampleList;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mItemName;
        public TextView mItemDescription;
        public TextView mItemPrice;
        public TextView mItemDate;
        public ImageView mItemimage;


        public ExampleViewHolder(View itemView ,final OnItemClickListener listener) {
            super(itemView);

            mItemName=itemView.findViewById(R.id.item_name);
            mItemPrice = itemView.findViewById(R.id.item_price);
            mItemDate = itemView.findViewById(R.id.item_date);
            mItemimage=itemView.findViewById(R.id.item_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public BookAdapter(ArrayList<BookItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        BookItem currentItem = mExampleList.get(position);
        holder.mItemName.setText(currentItem.getItemName());
        String priceval="Price: "+currentItem.getItemPrice()+"$";
        holder.mItemPrice.setText(priceval);
        String dateVal="Date: "+currentItem.getItemDate();
       
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
