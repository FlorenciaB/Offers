package paultr.offers.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  Use this for geofencing
 */
public class Event {

    private String type;
    private String message;
    private int priority;
    private int radius;
    private boolean reporting;
    private String route;
    private boolean server_message;

    public static Event fromJson( JSONObject jsonObject ) {
        //expect an actual event (i.e. enter) because events is an object, not an array in the data
        Event event = new Event();
        try {
            if( jsonObject.getJSONObject( "enter" ) != null ) {
                event.type = "enter";

                JSONObject object = jsonObject.getJSONObject( "enter" );
                event.message = object.getString( "message" );
                event.priority = object.getInt("priority");
                event.radius = object.getInt("radius");
                event.reporting = object.getBoolean("reporting");
                event.route = object.getString("route");
                event.server_message = object.getBoolean( "server_message" );
                return event;
            }

        } catch( JSONException e ) {

        }

        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isReporting() {
        return reporting;
    }

    public void setReporting(boolean reporting) {
        this.reporting = reporting;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isServer_message() {
        return server_message;
    }

    public void setServer_message(boolean server_message) {
        this.server_message = server_message;
    }
}
