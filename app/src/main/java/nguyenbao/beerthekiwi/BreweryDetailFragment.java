package nguyenbao.beerthekiwi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class BreweryDetailFragment extends Fragment {


    public BreweryDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_brewery_detail, container, false);
        setHasOptionsMenu(true);

        String jsonBrewery = "";
        Bundle extras = getArguments();
        if (extras != null) {
            jsonBrewery = extras.getString(BreweriesFragment.GSON_BREWERY);
        }
        Brewery selectedBrewery = new Gson().fromJson(jsonBrewery, Brewery.class);

        String name = selectedBrewery.getName();
        String description = selectedBrewery.getDescription();
        String website = selectedBrewery.getWebsite();
        String dateOfEstablishment = selectedBrewery.getDateOfEstablishment();

        TextView breweryName = (TextView)rootview.findViewById(R.id.brewery_name_detail_view);
        TextView breweryDescription = (TextView)rootview.findViewById(R.id.brewery_description_detail_view);
        TextView breweryWebsite = (TextView)rootview.findViewById(R.id.brewery_website_detail_view);
        TextView breweryDateOfEstablishment = (TextView)rootview.findViewById(R.id.brewery_date_of_establishment_detail_view);

        breweryName.setText(name);
        breweryDescription.setText(description);
        breweryWebsite.setText(website);
        breweryDateOfEstablishment.setText(dateOfEstablishment);

        return rootview;
    }

}
