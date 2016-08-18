package nguyenbao.beerthekiwi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String EXTRA_CITY = "nguyenbao.beerthekiwi.EXTRA_CITY";
    private static final String EXTRA_POSTAL = "nguyenbao.beerthekiwi.EXTRA_POSTAL";
    private static final String EXTRA_REGION = "nguyenbao.beerthekiwi.EXTRA_REGION";
    private static final String EXTRA_COUNTRY = "nguyenbao.beerthekiwi.EXTRA_COUNTRY";

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
        final EditText regionSearch = (EditText)rootview.findViewById(R.id.region_edit_view);
        final EditText countrySearch = (EditText)rootview.findViewById(R.id.country_edit_view);

        countrySearch.setText(R.string.default_country);

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

    //helper method, sends locality back to Main Activity
    private void sendIntent(String cityEntered, String postalCodeEntered, String regionEntered,
                            String countryEntered){
        Intent intent = new Intent(getActivity(), BreweryListActivity.class);
        intent.putExtra(EXTRA_CITY, cityEntered);
        intent.putExtra(EXTRA_POSTAL, postalCodeEntered);
        intent.putExtra(EXTRA_REGION, regionEntered);
        intent.putExtra(EXTRA_COUNTRY, countryEntered);
        startActivity(intent);
    }
}
