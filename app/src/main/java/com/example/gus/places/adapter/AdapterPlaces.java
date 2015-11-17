package com.example.gus.places.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gus.places.R;
import com.example.gus.places.fragment.DetailFragment;
import com.example.gus.places.holder.PlaceViewHolder;
import com.example.gus.places.model.Item;
import com.example.gus.places.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterPlaces extends RecyclerView.Adapter<PlaceViewHolder> {


    private static final String TAG = AdapterPlaces.class.getName();
    private final List<Item> mItemList;
    private final MainActivity mContext;
    private Item mCurrentItem;
    private DetailFragment mDetailFragment;

    public AdapterPlaces(Context context, List<Item> itemList) {
        mItemList = itemList;
        mContext = (MainActivity) context;

    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        PlaceViewHolder viewHolder = new PlaceViewHolder(view, new PlaceViewHolder.PlaceViewHolderClick() {
            @Override
            public void onClickRecyclerView(int position) {
                openActivityPicture(position);
            }
        });

        return viewHolder;
    }

    /**
     * Use this method to open the fragment of Detail item
     *
     * @param position is the item position of RecyclerView
     *
     */
    private void openActivityPicture(int position) {
        mCurrentItem = mItemList.get(position);

        if (mDetailFragment != null){
            mContext.getSupportFragmentManager().beginTransaction().remove(mDetailFragment);
        }

        FragmentManager fragmentManager = mContext.getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        }

        mDetailFragment =  DetailFragment.newInstance(position, mCurrentItem.getCityName());


        fragmentManager
                .beginTransaction()
                .replace(R.id.content_main_frame_container, mDetailFragment)
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        mCurrentItem = mItemList.get(position);

        //Download Image with Picasso
        Picasso.with(mContext.getApplicationContext())
                .load(R.drawable.wc_placeholder)
                .placeholder(R.drawable.wc_placeholder)
                .error(R.drawable.wc_placeholder)
                .into(holder.mImageView);

        //Set the name of the city.
        holder.mTextView.setText(mCurrentItem.getCityName());
    }

    public List<Item> getList() {
        return mItemList;
    }


    @Override
    public int getItemCount() {
        return (null != mItemList ? mItemList.size() : 0);
    }
}
