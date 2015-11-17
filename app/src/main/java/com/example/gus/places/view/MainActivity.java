package com.example.gus.places.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gus.places.R;
import com.example.gus.places.adapter.AdapterPlaces;
import com.example.gus.places.model.Item;
import com.example.gus.places.model.PlaceModel;
import com.example.gus.places.parcelable.PlaceParcelable;
import com.example.gus.places.service.PlaceService;
import com.example.gus.places.utils.Constants;
import com.example.gus.places.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;
    private AdapterPlaces mAdapterPlaces;
    private PlaceService mPlaceService;
    private RestAdapter mRestAdapter;
    private List<Item> mListItems;
    private ArrayAdapter<String> mAdapterAutoComplete;
    private AutoCompleteTextView mAutoComplete;
    private ProgressBar mProgressBar;
    private PlaceParcelable mPlaceParcelable;
    private String mCurrentCountryName;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Utils.isNetworkAvailable(MainActivity.this)){
            Toast.makeText(MainActivity.this, "Please connect to internet and try again", Toast.LENGTH_SHORT).show();
            return;
        }



        mCurrentCountryName = getResources().getConfiguration().locale.getDisplayCountry();

        if (savedInstanceState != null){
            Log.e(TAG,"saveInstanceState != null");
            mPlaceParcelable = savedInstanceState.getParcelable("list");
            mListItems = mPlaceParcelable.writeFromParcel();
            mCurrentCountryName = savedInstanceState.getString("countryName");

        }else{
            Log.e(TAG, "saveInstanceState == null");

        }

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);

        setupDrawerLayout();
        setupRecyclerView();
        setupProgressBar();
        setupAutoComplete();

        if (savedInstanceState == null){
            setCurrentLocation();
        }else{
            mAutoComplete.setSelected(false);
        }

    }

    private void setCurrentLocation() {
        mAutoComplete.setText(mCurrentCountryName);
        consumeWebService(mCurrentCountryName);
    }

    /**
     * Use this method to create the ToolBar support
     * and the icon home of the NavigationDrawer
     *
     * @return void.
     */
    public void setupDrawerLayout(){
        setSupportActionBar(mToolBar);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawerLayout.setDrawerListener(toogle);
        toogle.syncState();
    }



    /**
     * Use this method to setup the progressbar component
     *
     * @return void.
     */
    private void setupProgressBar() {
        mProgressBar = (ProgressBar) findViewById(R.id.content_main_progressBar);
    }

    /**
     * Use this method to setup the AutoCompletTextview
     * and add the action listener and adapter
     *
     * @return void.
     */
    private void setupAutoComplete() {
        mAutoComplete = (AutoCompleteTextView) findViewById(R.id.content_main_autoComplete);
        String[] contryList = getResources().getStringArray(R.array.countryList);
        mAdapterAutoComplete = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contryList);
        mAutoComplete.setAdapter(mAdapterAutoComplete);
        mAutoComplete.setThreshold(1);

        mAutoComplete.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String country = v.getText().toString();
                    mCurrentCountryName = v.getText().toString();

                    Utils.hideKeyboard(MainActivity.this);
                    consumeWebService(country);
                    removeFragment();

                    return true;
                }
                return false;
            }
        });

    }

    /**
     * Use this method to remove the current Fragment
     *
     * @return void.
     */
    private void removeFragment(){
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null && fragmentList.size() > 0){
            if (fragmentList.get(fragmentList.size()-1) != null){
                getSupportFragmentManager().beginTransaction().
                        remove(getSupportFragmentManager().findFragmentById(R.id.content_main_frame_container)).commit();
            }

        }

    }

    /**
     * Use this method to add Adapter to RecyclerView
     * and add Manager to RecyclerView
     *
     * @return void.
     */
    public void setupRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.content_view_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (mListItems == null){
            mListItems = new ArrayList<Item>();
        }

        mAdapterPlaces = new AdapterPlaces(MainActivity.this, mListItems);
        mRecyclerView.setAdapter(mAdapterPlaces);
    }

    /**
     * Use this method to consume the webservice
     * with Retrofit.
     *
     * @param country that you want the get the hotels.
     * @return void.
     */

    public void consumeWebService(final String country){

        mRestAdapter = new RestAdapter.Builder().setEndpoint(Constants.BASE_URL).build();
        mPlaceService = mRestAdapter.create(PlaceService.class);
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        mRecyclerView.setVisibility(RecyclerView.GONE);

        new AsyncTask<Void, Void, List<Item>>() {

            @Override
            protected List<Item> doInBackground(Void... params) {

                PlaceModel placeModel = mPlaceService.listPlaces(country); //get the current city of your preferences, localization
                return placeModel.getItems();
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                AdapterPlaces adapter = (AdapterPlaces) mRecyclerView.getAdapter();


                mListItems.clear();
                for (Item item : items){
                    mListItems.add(item);
                }
                adapter.notifyDataSetChanged();
                mProgressBar.setVisibility(ProgressBar.GONE);
                mRecyclerView.setVisibility(RecyclerView.VISIBLE);
            }

        }.execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        PlaceParcelable placeParcelable = new PlaceParcelable(mListItems);
        outState.putParcelable("list",placeParcelable);
        outState.putString("countryName",mCurrentCountryName);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
