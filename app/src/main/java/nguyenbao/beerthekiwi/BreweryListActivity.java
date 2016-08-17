package nguyenbao.beerthekiwi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BreweryListActivity extends AppCompatActivity {

    private static final String MESSAGE_KEY = "nguyenbao.beerthekiwi.MESSAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrieve extras from intent
        String message = getIntent().getStringExtra("nguyenbao.beerthekiwi.EXTRA_MESSAGE");

        //launch a fragment that displays listview of all breweries found in search result
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString(MESSAGE_KEY, message);
            BreweriesFragment fragment = new BreweriesFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
