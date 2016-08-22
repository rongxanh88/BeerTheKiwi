package nguyenbao.beerthekiwi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public static final String EXTRA_CITY = "nguyenbao.beerthekiwi.EXTRA_CITY";
    public static final String EXTRA_POSTAL = "nguyenbao.beerthekiwi.EXTRA_POSTAL";
    public static final String EXTRA_REGION = "nguyenbao.beerthekiwi.EXTRA_REGION";
    public static final String EXTRA_COUNTRY = "nguyenbao.beerthekiwi.EXTRA_COUNTRY";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        final EditText citySearch = (EditText)rootview.findViewById(R.id.locality_edit_view);
        final EditText postalSearch = (EditText)rootview.findViewById(R.id.postalCode_edit_view);

        final AutoCompleteTextView regionSearch = (AutoCompleteTextView) rootview.findViewById(R.id.region_edit_view);
        String[] regionAutoComplete = retrieveListOfRegions();
        ArrayAdapter<String> regionAdapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                        regionAutoComplete);
        regionSearch.setAdapter(regionAdapter);

        final AutoCompleteTextView countrySearch = (AutoCompleteTextView)
                rootview.findViewById(R.id.country_edit_view);
        ArrayList<String> countryAutoComplete = retrieveListOfCountries();

        ArrayAdapter<String> countryAdapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                        countryAutoComplete);
        countrySearch.setAdapter(countryAdapter);

//        countrySearch.setText(R.string.default_country);

        final Button button = (Button)rootview.findViewById(R.id.brewery_search);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String city = citySearch.getText().toString();
                String postalCode = postalSearch.getText().toString();
                String region = regionSearch.getText().toString();
                String country = countrySearch.getText().toString(); //default is United States

                sendIntent(city, postalCode, region, country);
            }
        });

        return rootview;
    }

    //helper method, sends locality back to Brewery List Activity
    private void sendIntent(String cityEntered, String postalCodeEntered, String regionEntered,
                            String countryEntered){
        Intent intent = new Intent(getActivity(), BreweryListActivity.class);
        intent.putExtra(EXTRA_CITY, cityEntered);
        intent.putExtra(EXTRA_POSTAL, postalCodeEntered);
        intent.putExtra(EXTRA_REGION, regionEntered);
        intent.putExtra(EXTRA_COUNTRY, countryEntered);
        startActivity(intent);
    }

    //helper method, sends back string array of countries for autocomplete
    private ArrayList<String> retrieveListOfCountries (){
        String[] countriesFullData = getResources().getStringArray(R.array.country_data);
        ArrayList<String> countryNameList = new ArrayList<>();

        for(int i = 0; i < countriesFullData.length; i++){
            Scanner stringScanner = new Scanner(countriesFullData[i]);
            String countryName = "";
            for(int j = 0; j < 10; j++){
                countryName = countryName + stringScanner.next() + " ";
                if(stringScanner.hasNextInt()){
                    break;
                }
                if(stringScanner.hasNext(Pattern.compile(".-..."))){
                    break;
                }
                if(stringScanner.hasNext(Pattern.compile(".-...."))){
                    break;
                }
                if(stringScanner.hasNext(Pattern.compile("..-...."))){
                    break;
                }
            }
            countryName = countryName.trim();
            countryNameList.add(countryName);
        }

        return countryNameList;
    }

    private String[] retrieveListOfRegions (){
        String[] regions = getResources().getStringArray(R.array.us_states);
        return regions;
    }
}
