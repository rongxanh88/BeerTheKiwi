package nguyenbao.beerthekiwi.BreweryObjects;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class Brewery {

    //class variables to store states of a brewery

    private BreweryLocation mBreweryLocation;
    private BreweryImageURL mImages;
    private String mID;
    private String mName;
    private String mDescription;
    private String mWebsite;
    private String mDateOfEstablishment;

    public Brewery(){
        //empty constructor
    }

    public Brewery(BreweryLocation breweryLocation, BreweryImageURL imageURL,String id, String name, String description,
                   String website, String dateOfEstablishment){
        mBreweryLocation = breweryLocation;
        mImages = imageURL;
        mID = id;
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

    public void setImages(BreweryImageURL images) {
        mImages = images;
    }

    public String getID() {
        return mID;
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
