package nguyenbao.beerthekiwi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class BreweryListAdapter extends ArrayAdapter<Brewery> {

    public BreweryListAdapter(Context context, int resource, List<Brewery> breweries) {
        super(context, resource, breweries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;// listItemView is assigned a recycled view
        //if recycled view is null, inflate a new view
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.brewery_list_item, parent, false);
        }

        return listItemView;
    }
}
