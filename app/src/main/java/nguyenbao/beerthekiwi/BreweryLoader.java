package nguyenbao.beerthekiwi;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class BreweryLoader extends AsyncTaskLoader<List<Brewery>> {

    private String mUrl;
    private static final String LOG_TAG = BreweryLoader.class.getName();

    public BreweryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Brewery> loadInBackground() {

        ArrayList<Brewery> breweries = FetchBreweryData.fetchBreweryData(mUrl);

        return breweries;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
