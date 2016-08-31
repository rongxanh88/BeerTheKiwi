package nguyenbao.beerthekiwi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import nguyenbao.beerthekiwi.BreweryObjects.Brewery;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class BreweryListAdapter extends ArrayAdapter<Brewery> {

    private static final String LOG_TAG = BreweryListAdapter.class.getName();

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

        String iconUrl = brewery.getImages().getLargeUrl();
        if (iconUrl != null) {
            //find image view and set icon
            new DownloadImageTask((ImageView) listItemView.findViewById(R.id.list_item_icon))
                    .execute(iconUrl);
        }else{
            ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
            iconView.setVisibility(View.INVISIBLE);
        }

        //find and set all views in layout
        TextView name = (TextView)listItemView.findViewById(R.id.brewery_name);
        name.setText(brewery.getName());

        return listItemView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(LOG_TAG, "Could not open stream to get image file.", e);
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
