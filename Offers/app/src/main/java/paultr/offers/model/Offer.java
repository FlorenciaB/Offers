package paultr.offers.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * There's a lot to this. I'll add things as I need them so I'm not writing a ton of boiler plate
 * on a test project
 *
 */
public class Offer {
    private String description;
    private String large_url;
    private String name;
    private int[] retailers;
    private String content;
    private String url;
    private int id;
    private boolean finished;

    public static Offer fromJson(JSONObject jsonObject) {
        Offer offer = new Offer();
        try {
            offer.description = jsonObject.getString( "description" );
            offer.large_url = jsonObject.getString( "large_url" );
            offer.name = jsonObject.getString( "name" );

            JSONArray retailersArray = jsonObject.optJSONArray( "retailers" );
            if( retailersArray != null ) {
                offer.retailers = new int[retailersArray.length()];
                for( int i = 0; i < retailersArray.length(); i++ ) {
                    offer.retailers[i] = retailersArray.optInt( i );
                }
            }

            offer.content = jsonObject.getString( "content" );
            offer.url = jsonObject.getString( "url" );
            offer.id = jsonObject.getInt( "id" );
            offer.finished = jsonObject.getBoolean( "finished" );

        } catch( JSONException e ) {

        }

        return offer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLarge_url() {
        return large_url;
    }

    public void setLarge_url(String large_url) {
        this.large_url = large_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getRetailers() {
        return retailers;
    }

    public void setRetailers(int[] retailers) {
        this.retailers = retailers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
