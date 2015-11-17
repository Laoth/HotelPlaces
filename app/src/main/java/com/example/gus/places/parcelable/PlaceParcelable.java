package com.example.gus.places.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.gus.places.model.Item;

import java.util.List;

/**
 * Created by Laotshi on 11/17/15.
 */
public class PlaceParcelable implements Parcelable {

    private  List<Item> mList;

    public PlaceParcelable(List<Item> list) {
        mList = list;
    }

    public PlaceParcelable(Parcel in){

    }

    public static final Creator<PlaceParcelable> CREATOR = new Creator<PlaceParcelable>() {
        @Override
        public PlaceParcelable createFromParcel(Parcel in) {
            return new PlaceParcelable(in);
        }

        @Override
        public PlaceParcelable[] newArray(int size) {
            return new PlaceParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mList);
    }

    public List<Item> writeFromParcel(){
        return mList;
    }
}
