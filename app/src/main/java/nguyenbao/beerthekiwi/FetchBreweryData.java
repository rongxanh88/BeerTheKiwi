package nguyenbao.beerthekiwi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class FetchBreweryData {

    private static final String LOG_TAG = FetchBreweryData.class.getName();

    private FetchBreweryData(){
        //empty constructor so this cannot be instatiated
    }

    public static ArrayList<Brewery> fetchBreweryData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create a list object
        ArrayList<Brewery> breweries = extractBreweries(jsonResponse);

        return breweries;
    }

    //helper method to parse JSON response
    private static ArrayList<Brewery> extractBreweries(String jsonString) {

        // Create an empty ArrayList that we can start adding breweries to
        ArrayList<Brewery> breweries = new ArrayList<>();

        // Try to parse the string jsonString. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            //Convert JSON_RESPONSE String into a JSONObject
            JSONObject reader = new JSONObject(jsonString);

            //grab JSONArray named "data"
            JSONArray data = reader.getJSONArray("data");

            //loop through every object in data array
            for (int i = 0; i < data.length(); i++){
                //grab object at position i
                JSONObject brewery = data.getJSONObject(i);

                //grab location data
                String streetAddress = brewery.getString("streetAddress");
                String locality = brewery.getString("locality");
                String region = brewery.getString("region");
                String postalCode = brewery.getString("postalCode");
                JSONObject country = brewery.getJSONObject("country");
                String countryName = country.getString("displayName");
                double longitude = brewery.getDouble("longitude");
                double latitude = brewery.getDouble("latitude");

                //create location object with all location data
                BreweryLocation breweryLocation = new BreweryLocation(
                        streetAddress, locality, region, postalCode, countryName,
                        longitude, latitude);

                //grab brewery data
                JSONObject breweryDetails = brewery.getJSONObject("brewery");
                String name = breweryDetails.getString("name");
                String description = breweryDetails.getString("description");
                String website = breweryDetails.getString("website");
                String dateOfEstablish = breweryDetails.getString("established");

                breweries.add(new Brewery(breweryLocation, name, description,
                        website, dateOfEstablish));
            }


            //create local variables, which read from JSON objects
             //create Brewery arraylist and return arraylist of breweries
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }

        // Return the list of earthquakes
        return breweries;
    }


    //JSON data is read and then put into the arrayList

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with IOException for JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
