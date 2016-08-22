package nguyenbao.beerthekiwi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BreweryDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Activity will launch a fragment that displays a detail view of the brewery
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            BreweryDetailFragment fragment = new BreweryDetailFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
