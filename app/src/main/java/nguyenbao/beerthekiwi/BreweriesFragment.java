package nguyenbao.beerthekiwi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BreweriesFragment extends Fragment {


    public BreweriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_breweries, container, false);
        setHasOptionsMenu(true);

        //check for network connection and call loader to grab network data to put into arraylist
        //create ArrayList object of breweries
        final ArrayList<Brewery> breweries = new ArrayList<Brewery>();
        //test with dummy data
        breweries.add(new Brewery("Test ArrayList"));
        breweries.add(new Brewery("Test ArrayList1"));
        breweries.add(new Brewery("Test ArrayList2"));
        breweries.add(new Brewery("Test ArrayList3"));
        breweries.add(new Brewery("Test ArrayList4"));

        //create adapterview for breweries
        ArrayAdapter<Brewery> itemsAdapter = new ArrayAdapter<Brewery>
                (getActivity(), android.R.layout.simple_list_item_1, breweries);

        //set arraylist into adapterview
        ListView listView = (ListView)rootview.findViewById(R.id.brewery_list_view);

        //bind adapterview to root view
        listView.setAdapter(itemsAdapter);

        return rootview;
    }

}
