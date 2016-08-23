package nguyenbao.beerthekiwi.BreweryObjects;

/**
 * Created by Bao Nguyen on 8/23/2016.
 */
public class BreweryImageURL {

    private String mIconUrl;
    private String mMediumUrl;
    private String mLargeUrl;

    public BreweryImageURL() {
        //empty constructor
    }

    public BreweryImageURL(String iconUrl, String mediumUrl, String largeUrl) {
        mIconUrl = iconUrl;
        mMediumUrl = mediumUrl;
        mLargeUrl = largeUrl;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public String getMediumUrl() {
        return mMediumUrl;
    }

    public String getLargeUrl() {
        return mLargeUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        mMediumUrl = mediumUrl;
    }

    public void setLargeUrl(String largeUrl) {
        mLargeUrl = largeUrl;
    }
}
