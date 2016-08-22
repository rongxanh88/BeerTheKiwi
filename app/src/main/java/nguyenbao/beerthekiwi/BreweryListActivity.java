package nguyenbao.beerthekiwi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BreweryListActivity extends AppCompatActivity {

    public static final String CITY_KEY = "nguyenbao.beerthekiwi.CITY_KEY";
    public static final String POSTAL_KEY = "nguyenbao.beerthekiwi.POSTAL_KEY";
    public static final String REGION_KEY = "nguyenbao.beerthekiwi.REGION_KEY";
    public static final String COUNTRY_KEY = "nguyenbao.beerthekiwi.COUNTRY_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrieve extras from intent
        String city = getIntent().getStringExtra(SearchFragment.EXTRA_CITY);
        String postalCode = getIntent().getStringExtra(SearchFragment.EXTRA_POSTAL);
        String region = getIntent().getStringExtra(SearchFragment.EXTRA_REGION);
        String country = getIntent().getStringExtra(SearchFragment.EXTRA_COUNTRY);

        //launch a fragment that displays listview of all breweries found in search result
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString(CITY_KEY, city);
            bundle.putString(POSTAL_KEY, postalCode);
            bundle.putString(REGION_KEY, region);
            bundle.putString(COUNTRY_KEY, country);
            BreweriesFragment fragment = new BreweriesFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
