package nguyenbao.beerthekiwi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BreweryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //launch a fragment that displays listview of all breweries found in search result
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, new BreweriesFragment())
                    .commit();
        }
    }
}
