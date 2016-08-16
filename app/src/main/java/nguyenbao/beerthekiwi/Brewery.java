package nguyenbao.beerthekiwi;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class Brewery {

    //global variables to store states of a brewery

    private BreweryLocation mBreweryLocation;
    private String mName;
    private String mDescription;
    private String mWebsite;
    private String mDateOfEstablishment;
    private String mTestData;

    public Brewery(){
        //empty constructor
    }

    public Brewery(String testData){
        mTestData = testData;
    }

    public Brewery(BreweryLocation breweryLocation, String name, String description,
                   String website, String dateOfEstablishment){
        mBreweryLocation = breweryLocation;
        mName = name;
        mDescription = description;
        mWebsite = website;
        mDateOfEstablishment = dateOfEstablishment;
    }

    //get methods for Brewery states
    public BreweryLocation getBreweryLocation() {
        return mBreweryLocation;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getDateOfEstablishment() {
        return mDateOfEstablishment;
    }

    public String getTestData() {
        return mTestData;
    }
}
