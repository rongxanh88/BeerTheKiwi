package nguyenbao.beerthekiwi.BreweryObjects;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class Brewery {

    //class variables to store states of a brewery

    private BreweryLocation mBreweryLocation;
    private BreweryImageURL mImages;
    private String mName;
    private String mDescription;
    private String mWebsite;
    private String mDateOfEstablishment;

    public Brewery(){
        //empty constructor
    }

    public Brewery(BreweryLocation breweryLocation, BreweryImageURL images, String name, String description,
                   String website, String dateOfEstablishment){
        mBreweryLocation = breweryLocation;
        mImages = images;
        mName = name;
        mDescription = description;
        mWebsite = website;
        mDateOfEstablishment = dateOfEstablishment;
    }

    //get methods for Brewery states
    public BreweryLocation getBreweryLocation() {
        return mBreweryLocation;
    }

    public BreweryImageURL getImages() {
        return mImages;
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
}
