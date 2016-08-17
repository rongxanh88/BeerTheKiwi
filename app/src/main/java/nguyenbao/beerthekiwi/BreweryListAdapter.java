package nguyenbao.beerthekiwi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        final Brewery brewery = getItem(position);

        //find and set all views in layout
        TextView name = (TextView)listItemView.findViewById(R.id.brewery_name);
        name.setText(brewery.getName());

        TextView address = (TextView)listItemView.findViewById(R.id.brewery_address);
        address.setText(brewery.getBreweryLocation().getStreetAddress());

//        TextView postalCode = (TextView)listItemView.findViewById(R.id.brewery_postal_code);
//        postalCode.setText(brewery.getBreweryLocation().getPostalCode());

        return listItemView;
    }
}
