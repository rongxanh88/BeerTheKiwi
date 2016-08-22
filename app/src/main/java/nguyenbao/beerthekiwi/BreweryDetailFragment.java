//package nguyenbao.beerthekiwi;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//
//public class BreweryDetailFragment extends Fragment {
//
//
//    public BreweryDetailFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootview = inflater.inflate(R.layout.fragment_brewery_detail, container, false);
//        setHasOptionsMenu(true);
//
//        String name = getArguments().getString("nguyenbao.beerthekiwi.NAME_KEY");
//        String description = getArguments().getString("nguyenbao.beerthekiwi.DESCRIPTION_KEY");
//        String website = getArguments().getString("nguyenbao.beerthekiwi.WEBSITE_KEY");
//        String dateOfEstablishment = getArguments().getString("nguyenbao.beerthekiwi.DATEOFESTABLISHMENT_KEY");
//
//        TextView breweryName = (TextView)rootview.findViewById(R.id.brewery_name_detail_view);
//        TextView breweryDescription = (TextView)rootview.findViewById(R.id.brewery_description_detail_view);
//        TextView breweryWebsite = (TextView)rootview.findViewById(R.id.brewery_website_detail_view);
//        TextView breweryDateOfEstablishment = (TextView)rootview.findViewById(R.id.brewery_date_of_establishment_detail_view);
//
//        breweryName.setText(name);
//        breweryDescription.setText(description);
//        breweryWebsite.setText(website);
//        breweryDateOfEstablishment.setText(dateOfEstablishment);
//
//        return rootview;
//    }
//
//}
