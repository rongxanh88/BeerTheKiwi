package nguyenbao.beerthekiwi;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.InputStream;

import nguyenbao.beerthekiwi.BreweryObjects.Brewery;

public class BreweryDetailFragment extends Fragment {

    private static final String LOG_TAG = BreweryDetailFragment.class.getName();
    private static final String BASE_GEO_URI = "geo:";

    public BreweryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_brewery_detail, container, false);
        setHasOptionsMenu(true);

        Bundle extras = getArguments();
        final Brewery selectedBrewery = getBrewery(extras);

        String imageUrl = selectedBrewery.getImages().getLargeUrl();
        if (imageUrl != null) {
            //find image view and set icon
            new DownloadImageTask((ImageView) rootview.findViewById(R.id.brewery_detail_image))
                    .execute(imageUrl);
        }else{
            ImageView iconView = (ImageView) rootview.findViewById(R.id.brewery_detail_image);
            iconView.setVisibility(View.GONE);
        }

        final TextView breweryName = (TextView)rootview.findViewById(R.id.brewery_name_detail_view);
        breweryName.setText(selectedBrewery.getName());

        TextView breweryDescription = (TextView)rootview.findViewById(R.id.brewery_description_detail_view);
        breweryDescription.setText(selectedBrewery.getDescription());

        //Viewgroup for location
        final double latitude = selectedBrewery.getBreweryLocation().getLatitude();
        final double longitude = selectedBrewery.getBreweryLocation().getLongitude();
        double invalid = FetchBreweryData.INVALID_VALUE;

        String firstCardinal;
        if (latitude >= 0){
            firstCardinal = "N";
        }else{
            firstCardinal = "S";
        }
        String secondCardinal;
        if (longitude >= 0){
            secondCardinal = "E";
        }else{
            secondCardinal = "W";
        }

        final String address = selectedBrewery.getBreweryLocation().getStreetAddress();
        final String locality = selectedBrewery.getBreweryLocation().getLocality();
        final String region = selectedBrewery.getBreweryLocation().getRegion();

        ViewGroup locationView = (ViewGroup)rootview.findViewById(R.id.brewery_location_view_group);
        locationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brewery = selectedBrewery.getName();
                Uri locationByCoord = Uri.parse(BASE_GEO_URI + latitude + "," + longitude + "?")
                        .buildUpon()
                        .appendQueryParameter("q", address + ", " + locality + ", " + region + "(" + brewery + ")")
                        .build();
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationByCoord);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        TextView breweryAddress = (TextView)rootview.findViewById(R.id.brewery_detail_address);
        if (address.length() > 0){
            breweryAddress.setText("Location: \n" + address + " " + locality + " " + region);
        }
        else if ((longitude != invalid) && (latitude != invalid)){
            TextView breweryLatitude = (TextView)rootview.findViewById(R.id.brewery_detail_latitude);
            breweryLatitude.setText("" + latitude + firstCardinal);

            TextView breweryLongitude = (TextView)rootview.findViewById(R.id.brewery_detail_longitude);
            breweryLongitude.setText("" + longitude + secondCardinal);
        }

        TextView breweryWebsite = (TextView)rootview.findViewById(R.id.brewery_website_detail_view);
        breweryWebsite.setText("Website: " + selectedBrewery.getWebsite());
        breweryWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create url string
                String url = selectedBrewery.getWebsite();

                //create intent
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        TextView breweryDateOfEstablishment = (TextView)rootview.findViewById(R.id.brewery_date_of_establishment_detail_view);
        breweryDateOfEstablishment.setText("Established: " + selectedBrewery.getDateOfEstablishment());

        return rootview;
    }

    private Brewery getBrewery (Bundle extras){
        String jsonBrewery = "";
        if (extras != null) {
            jsonBrewery = extras.getString(BreweriesFragment.GSON_BREWERY);
        }
        return new Gson().fromJson(jsonBrewery, Brewery.class);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap breweryLogo = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                breweryLogo = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Could not open stream to get image file.", e);
            }
            return breweryLogo;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
