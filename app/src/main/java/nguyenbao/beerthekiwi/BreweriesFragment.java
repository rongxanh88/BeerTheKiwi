package nguyenbao.beerthekiwi;


import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BreweriesFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<Brewery>> {

    private static final String LOG_TAG = BreweriesFragment.class.getName();
    private static final String BASE_URL = "http://api.brewerydb.com/v2/locations/?";
    private static final String PARAM_KEY = "key";
    private static final String PARAM_FORMAT = "format";
    private static final String PARAM_LOCALITY = "locality";
    private static final String PARAM_COUNTRY = "countryIsoCode";

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

        final ArrayList<Brewery> breweries = new ArrayList<Brewery>();

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

        return rootview;
    }

    @Override
    public Loader<List<Brewery>> onCreateLoader(int id, Bundle args) {

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_KEY, "c1ecd34119b27016f28060879cbc13e0")
                .appendQueryParameter(PARAM_FORMAT, "json")
                .appendQueryParameter(PARAM_LOCALITY, "Boulder")
                .appendQueryParameter(PARAM_COUNTRY, "US")
                .build();

        return new BreweryLoader(getActivity(), builtUri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Brewery>> loader, List<Brewery> data) {
        mBreweryArrayAdapter.clear();
        mBreweryArrayAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Brewery>> loader) {
        mBreweryArrayAdapter.clear();
    }
}
