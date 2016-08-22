package nguyenbao.beerthekiwi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BreweryDetailActivity extends AppCompatActivity
        implements BreweriesFragment.OnSelectedBreweryListener{

    private static final String NAME_KEY = "nguyenbao.beerthekiwi.NAME_KEY";
    private static final String DESCRIPTION_KEY = "nguyenbao.beerthekiwi.DESCRIPTION_KEY";
    private static final String WEBSITE_KEY = "nguyenbao.beerthekiwi.WEBSITE_KEY";
    private static final String DATEOFESTABLISHMENT_KEY = "nguyenbao.beerthekiwi.DATEOFESTABLISHMENT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBrewerySelected(Brewery selectedBrewery) {
        String name = selectedBrewery.getName();
        String description = selectedBrewery.getDescription();
        String website = selectedBrewery.getWebsite();
        String dateOfEstablishment = selectedBrewery.getDateOfEstablishment();

        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        bundle.putString(DESCRIPTION_KEY, description);
        bundle.putString(WEBSITE_KEY, website);
        bundle.putString(DATEOFESTABLISHMENT_KEY, dateOfEstablishment);

        BreweryDetailFragment fragment = new BreweryDetailFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
    }
}
