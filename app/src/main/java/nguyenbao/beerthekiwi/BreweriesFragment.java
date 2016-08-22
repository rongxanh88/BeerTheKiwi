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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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

    private ListView mListView;
    private BreweryListAdapter mBreweryArrayAdapter;

//    OnSelectedBreweryListener mCallback;
//
//    public interface OnSelectedBreweryListener {
//        void onBrewerySelected(Brewery selectedBrewery);
//    }

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
                Toast.makeText(getContext(), "Clicked on item" + position, Toast.LENGTH_SHORT).show();

//                Brewery selectedBrewery = (Brewery)mListView.getItemAtPosition(position);
//
//                mCallback.onBrewerySelected(selectedBrewery);
            }
        });

        return rootview;
    }

    @Override
    public Loader<List<Brewery>> onCreateLoader(int id, Bundle args) {

        String city = getArguments().getString("nguyenbao.beerthekiwi.CITY_KEY");
        String postalCode = getArguments().getString("nguyenbao.beerthekiwi.POSTAL_KEY");
        String region = getArguments().getString("nguyenbao.beerthekiwi.REGION_KEY");
        //String country = getArguments().getString("nguyenbao.beerthekiwi.COUNTRY_KEY");

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_KEY, "c1ecd34119b27016f28060879cbc13e0")
                .appendQueryParameter(PARAM_FORMAT, "json")
                .appendQueryParameter(PARAM_COUNTRY, "US")
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

        return new BreweryLoader(getActivity(), builtUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Brewery>> loader, List<Brewery> data) {
        mBreweryArrayAdapter.clear();

        if(data.size() == 0) {
            returnToSearch();
        }
        else if(data.size() == 50){
            mBreweryArrayAdapter.addAll(data);
            Toast.makeText(getActivity(), "Max results reached: 50", Toast.LENGTH_LONG).show();
        }else{
            mBreweryArrayAdapter.addAll(data);
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

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        // This makes sure that the container activity has implemented
//        // the callback interface. If not, it throws an exception
//        try {
//            mCallback = (OnSelectedBreweryListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnSelectedBreweryListener");
//        }
//    }
}
