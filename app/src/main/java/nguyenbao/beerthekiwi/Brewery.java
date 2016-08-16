package nguyenbao.beerthekiwi;

/**
 * Created by Bao Nguyen on 8/17/2016.
 */
public class Brewery {

    //global variables to store states of a brewery

    //location object

    private String mName;
    private String mDescription;
    private String mWebsite;
    private String mDateOfEstablishment;

    public Brewery(){
        //empty constructor
    }

    public Brewery(String name, String description, String website, String dateOfEstablishment){
        mName = name;
        mDescription = description;
        mWebsite = website;
        mDateOfEstablishment = dateOfEstablishment;
    }

    //get methods for Brewery states

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
