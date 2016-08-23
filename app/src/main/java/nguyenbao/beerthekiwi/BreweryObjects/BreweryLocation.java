package nguyenbao.beerthekiwi.BreweryObjects;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class BreweryLocation {

    //states of location
    private String mStreetAddress;
    private String mLocality;
    private String mRegion;
    private String mPostalCode;
    private String mCountryName;
    private double mLongitude;
    private double mLatitude;

    public BreweryLocation(){
        //empty constructor
    }

    public BreweryLocation(String streetAddress, String locality, String region,String postalCode,
                           String countryName, double longitude, double latitude){

        mStreetAddress = streetAddress;
        mLocality = locality;
        mRegion = region;
        mPostalCode = postalCode;
        mCountryName = countryName;
        mLongitude = longitude;
        mLatitude = latitude;
    }

    public String getStreetAddress() {
        return mStreetAddress;
    }

    public String getLocality() {
        return mLocality;
    }

    public String getRegion() {
        return mRegion;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }
}
