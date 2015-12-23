package paultr.offers.utility;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import paultr.offers.R;
import paultr.offers.model.Offer;
import paultr.offers.model.Retailer;
import paultr.offers.model.StoreLocation;

/**
    Should use a database or something, but this is fine for a demo.
 */
public class DataUtil {
    private static DataUtil dataUtil = new DataUtil();

    private List<Offer> offers = new ArrayList<>();
    private List<Retailer> retailers = new ArrayList<>();
    private List<StoreLocation> storeLocations = new ArrayList<>();

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
            for( int i = 0; i < array.length(); i++ ) {
                offers.add( Offer.fromJson( array.getJSONObject( i ) ) );
            }

        } catch( JSONException e ) {}
    }

    private void initRetailers( Context context ) {
        String json = loadJSONFromResource( context , R.raw.retailers );
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray( "retailers" );
            for( int i = 0; i < array.length(); i++ ) {
                retailers.add( Retailer.fromJson( array.getJSONObject( i ) ) );
            }

        } catch( JSONException e ) {}
    }

    private void initStoreLocations( Context context ) {
        String json = loadJSONFromResource( context , R.raw.storelocations );
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray( "stores" );
            for( int i = 0; i < array.length(); i++ ) {
                storeLocations.add( StoreLocation.fromJson( array.getJSONObject( i ) ) );
            }

        } catch( JSONException e ) {}
    }

    public static DataUtil getInstance() {
        return dataUtil;
    }

    @NonNull
    public List<Offer> getOffers() {
        return offers;
    }

    @NonNull
    public List<StoreLocation> getStoreLocations() {
        return storeLocations;
    }

    @NonNull
    public List<Retailer> getRetailers() {
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
