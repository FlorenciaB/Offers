package paultr.offers.model;


import android.util.Log;

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
    private String url;
    private int id;
    private boolean finished;

    public static Offer fromJson(JSONObject jsonObject) {
        Offer offer = new Offer();
        try {
            if( jsonObject.has( "description" ) ) {
                offer.description = jsonObject.getString("description");
            }
            if( jsonObject.has( "large_url" ) ) {
                offer.large_url = jsonObject.getString("large_url");
            }

            if( jsonObject.has( "name" ) ) {
                offer.name = jsonObject.getString("name");
            }

            if( jsonObject.has( "retailers" ) ) {
                JSONArray retailersArray = jsonObject.optJSONArray("retailers");
                if (retailersArray != null) {
                    offer.retailers = new int[retailersArray.length()];
                    for (int i = 0; i < retailersArray.length(); i++) {
                        offer.retailers[i] = retailersArray.optInt(i);
                    }
                }
            }

            if( jsonObject.has( "url" ) ) {
                offer.url = jsonObject.getString("url");
            }

            if( jsonObject.has( "finished" ) ) {
                offer.finished = jsonObject.getBoolean("finished");
            }

            offer.id = jsonObject.getInt( "id" );

        } catch( JSONException e ) {
            Log.e( "Offer", "JsonException: " + e.getMessage() );

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
