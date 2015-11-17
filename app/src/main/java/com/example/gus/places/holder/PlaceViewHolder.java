package com.example.gus.places.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gus.places.R;

/**
 * Created by Laotshi on 11/14/15.
 */
public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final ImageView mImageView;
    public final TextView mTextView;
    private final PlaceViewHolderClick mListenerClick;
    private int mPosition;

    public PlaceViewHolder(View itemView, PlaceViewHolderClick listenerClick) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.item_layout_imageView);
        mTextView = (TextView) itemView.findViewById(R.id.item_layout_textView);
        mListenerClick = listenerClick;

        mImageView.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    public void setPosition(int position){
        mPosition = position;
    }

    @Override
    public void onClick(View v) {
        mListenerClick.onClickRecyclerView(getAdapterPosition());
    }

    public interface PlaceViewHolderClick{
        void onClickRecyclerView(int position);
    }
}
