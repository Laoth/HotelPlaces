package com.example.gus.places.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gus.places.R;
import com.squareup.picasso.Picasso;

/**
 * It is the fragment for open the detail of the item
 */
public class DetailFragment extends Fragment {

    private static final String ARG_POSITION_ITEM = "position";
    private static final String ARG_COUNTRY_NAME = "contryName";

    private String mPositionItem;
    private String mCountryName;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position is the item position.
     * @param countryName is the name of the country.
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(int position, String countryName) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_POSITION_ITEM, String.valueOf(position));
        args.putString(ARG_COUNTRY_NAME, countryName);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPositionItem = getArguments().getString(ARG_POSITION_ITEM);
            mCountryName = getArguments().getString(ARG_COUNTRY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textViewTitle = (TextView) view.findViewById(R.id.fragment_detail_name);
        textViewTitle.setText(mCountryName);

        TextView textViewPrice = (TextView) view.findViewById(R.id.fragment_detail_price);
        textViewPrice.setText("$" + mPositionItem);

        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_detail_picture);

        Picasso.with(view.getContext())
                .load(R.drawable.wc_placeholder)
                .placeholder(R.drawable.wc_placeholder)
                .error(R.drawable.wc_placeholder)
                .into(imageView);

        return view;
    }
}
