package nguyenbao.beerthekiwi;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nguyenbao.beerthekiwi.BreweryObjects.Brewery;


/**
 * A simple {@link Fragment} subclass.
 */
public class BreweriesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Brewery>> {

    private static final String LOG_TAG = BreweriesFragment.class.getName();

    //For use with Uri Builder
    private static final String BASE_URL = "http://api.brewerydb.com/v2/locations/?";
    private static final String PARAM_KEY = "key";
    private static final String PARAM_FORMAT = "format";
    private static final String PARAM_LOCALITY = "locality";
    private static final String PARAM_POSTAL = "postalCode";
    private static final String PARAM_REGION = "region";
    private static final String PARAM_COUNTRY = "countryIsoCode";

    //GSON OBJECT STRING
    public static final String GSON_BREWERY = "nguyenbao.beerthekiwi.GSON_BREWERY";

    private ListView mListView;
    private BreweryListAdapter mBreweryArrayAdapter;

    public BreweriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_breweries, container, false);
        setHasOptionsMenu(true);

        final ArrayList<Brewery> breweries = new ArrayList<>();

        //initialize the loader if network connection state is good
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //initializes loader
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
        } else {
            // display error
            Log.e(LOG_TAG, "No Network Connectivity");
        }

        //create adapterview for breweries
        mBreweryArrayAdapter = new BreweryListAdapter(getActivity(), 0, breweries);

        //set arraylist into adapterview
        mListView = (ListView)rootview.findViewById(R.id.brewery_list_view);

        //bind adapterview to root view
        mListView.setAdapter(mBreweryArrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Brewery selectedBrewery = (Brewery)mListView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), BreweryDetailActivity.class);
                intent.putExtra(GSON_BREWERY, new Gson().toJson(selectedBrewery));
                startActivity(intent);
            }
        });

        return rootview;
    }

    @Override
    public Loader<List<Brewery>> onCreateLoader(int id, Bundle args) {

        String city = getArguments().getString(BreweryListActivity.CITY_KEY);
        String postalCode = getArguments().getString(BreweryListActivity.POSTAL_KEY);
        String region = getArguments().getString(BreweryListActivity.REGION_KEY);
        String country = getArguments().getString(BreweryListActivity.COUNTRY_KEY);

        String countryCode = countryNameToCode(country);

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_KEY, "c1ecd34119b27016f28060879cbc13e0")
                .appendQueryParameter(PARAM_FORMAT, "json")
                .build();

        if(city.length() != 0){
            builtUri = builtUri.buildUpon()
                    .appendQueryParameter(PARAM_LOCALITY, city)
                    .build();
        }
        if(postalCode.length() != 0){
            builtUri = builtUri.buildUpon()
                    .appendQueryParameter(PARAM_POSTAL, postalCode)
                    .build();
        }
        if(region.length() != 0){
            builtUri = builtUri.buildUpon()
                    .appendQueryParameter(PARAM_REGION, region)
                    .build();
        }
        if(countryCode.length() != 0){
            builtUri = builtUri.buildUpon()
                    .appendQueryParameter(PARAM_COUNTRY, countryCode)
                    .build();
        }

        return new BreweryLoader(getActivity(), builtUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Brewery>> loader, List<Brewery> data) {
        mBreweryArrayAdapter.clear();

        if(data.size() > 0){
            mBreweryArrayAdapter.addAll(data);
        }
        if(data.size() == 0) {
            returnToSearch();
        }
        if(data.size() == 50){
            mBreweryArrayAdapter.addAll(data);
            Toast.makeText(getActivity(), "Max results reached: 50", Toast.LENGTH_LONG).show();
        }else{
            //no other considerations
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Brewery>> loader) {
        mBreweryArrayAdapter.clear();
    }

    private void returnToSearch(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);

        Toast.makeText(getActivity(), "No Search Results", Toast.LENGTH_LONG).show();
    }

    private String countryNameToCode(String countryName){
        String[] countriesFullData = getResources().getStringArray(R.array.country_data);
        String twoDigitCode = null;

        int i = 0;
        while (twoDigitCode == null){
            String countryString = countriesFullData[i];
            if (countryString.contains(countryName)){
                String[] country = countryString.split(" ");
                int endOfArray = country.length;
                twoDigitCode = country[endOfArray - 2];
            }
            i++;
        }
        if (twoDigitCode == null) {
            return null;
        }else{
            return twoDigitCode;
        }
    }
}
