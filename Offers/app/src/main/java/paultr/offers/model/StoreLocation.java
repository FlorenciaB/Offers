package paultr.offers.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Paul on 12/22/15.
 */
public class StoreLocation implements ClusterItem {

    private Event event = null;
    private int id;
    private int retailer_id;
    private double latitude;
    private double longitude;
    private LatLng location;

    public static StoreLocation fromJson( JSONObject jsonObject ) {

        StoreLocation storeLocation = new StoreLocation();

        try {
            storeLocation.id = jsonObject.getInt( "id" );
            if( jsonObject.getJSONObject( "events" ) != null ) {
                storeLocation.event = Event.fromJson( jsonObject.getJSONObject( "events" ) );
            }

            storeLocation.retailer_id = jsonObject.getInt( "retailer_id" );
            storeLocation.latitude = jsonObject.getDouble( "lat" );
            storeLocation.longitude = jsonObject.getDouble( "long" );
            storeLocation.location = new LatLng( storeLocation.latitude, storeLocation.longitude );
        } catch( JSONException e ) {

        }

        return storeLocation;

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRetailer_id() {
        return retailer_id;
    }

    public void setRetailer_id(int retailer_id) {
        this.retailer_id = retailer_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng( latitude, longitude );
    }
}
