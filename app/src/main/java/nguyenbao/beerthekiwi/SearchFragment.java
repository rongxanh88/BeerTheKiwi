package nguyenbao.beerthekiwi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "nguyenbao.beerthekiwi.EXTRA_MESSAGE";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        EditText citySearch = (EditText)rootview.findViewById(R.id.locality_text_view);
        citySearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //On send, use helper method to send data back to Main Activity
                    String city = v.getText().toString();
                    sendIntent(city);
                    handled = true;
                }
                return handled;
            }
        });

        return rootview;
    }

    //helper method, sends locality back to Main Activity
    private void sendIntent(String textEntered){
        Intent intent = new Intent(getActivity(), BreweryListActivity.class);
        String message = textEntered;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
