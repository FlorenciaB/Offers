package paultr.offers.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import paultr.offers.R;
import paultr.offers.model.Offer;
import paultr.offers.model.Retailer;
import paultr.offers.model.StoreLocation;

/**
    Should use a database or something, but this is fine for a demo.
 */
public class DataUtil {
    private static DataUtil dataUtil = new DataUtil();

    private Map<Integer, Offer> offers = new HashMap<>();
    private Map<Integer, Retailer> retailers = new HashMap<>();
    private Map<Integer, StoreLocation> storeLocations = new HashMap<>();

    private DataUtil() {
    }

    public void init( Context context ) {
        initOffers( context );
        initRetailers( context );
        initStoreLocations( context );
    }

    private void initOffers( Context context ) {
        String json = loadJSONFromResource( context , R.raw.offers );
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray( "offers" );
            Offer offer;
            for( int i = 0; i < array.length(); i++ ) {
                offer = Offer.fromJson( array.getJSONObject( i ) );
                offers.put( offer.getId(), offer );
            }

        } catch( JSONException e ) {}
    }

    private void initRetailers( Context context ) {
        String json = loadJSONFromResource( context , R.raw.retailers );
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray( "retailers" );
            Retailer retailer;
            for( int i = 0; i < array.length(); i++ ) {
                retailer = Retailer.fromJson( array.getJSONObject( i ) );
                retailers.put( retailer.getId(), retailer );
            }

        } catch( JSONException e ) {}
    }

    private void initStoreLocations( Context context ) {
        String json = loadJSONFromResource( context , R.raw.storelocations );
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray( "stores" );
            StoreLocation location;
            for( int i = 0; i < array.length(); i++ ) {
                location = StoreLocation.fromJson( array.getJSONObject(i) );
                storeLocations.put( location.getId(), location );
            }

        } catch( JSONException e ) {}
    }

    public static DataUtil getInstance() {
        return dataUtil;
    }

    @NonNull
    public Map<Integer, Offer> getOffers() {
        return offers;
    }

    @NonNull
    public Map<Integer, StoreLocation> getStoreLocations() {
        return storeLocations;
    }

    @NonNull
    public Map<Integer, Retailer> getRetailers() {
        return retailers;
    }

    public static String loadJSONFromResource( Context context, int resource ) {
        if( resource <= 0 || context == null )
            return null;

        String json = null;
        InputStream is = context.getResources().openRawResource( resource );
        try {
            if( is != null ) {
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                json = new String(buffer, "UTF-8");
            }
        } catch( IOException e ) {
            return null;
        } finally {
            try {
                if( is != null )
                    is.close();
            } catch( IOException e ) {}
        }

        return json;
    }

}
