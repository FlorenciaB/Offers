package paultr.offers.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Only adding basics, don't need to keep track of everything
 */
public class Retailer {

    private String any_brand_full_url;
    private String any_brand_icon_url;
    private String card_signup_url;
    private int[] category_ids;
    private String exclusive_image_url;
    private String icon_url;
    private String name;
    private int id;


    public static Retailer fromJson( JSONObject jsonObject ) {
        Retailer retailer = new Retailer();

        try {
            retailer.any_brand_full_url = jsonObject.getString( "any_brand_full_url" );
            retailer.any_brand_icon_url = jsonObject.getString( "any_brand_icon_url" );
            retailer.card_signup_url = jsonObject.getString( "card_signup_url" );
            retailer.exclusive_image_url = jsonObject.getString( "exclusive_image_url" );
            retailer.icon_url = jsonObject.getString( "icon_url" );
            retailer.name = jsonObject.getString( "name" );
            retailer.id = jsonObject.getInt( "id" );

            JSONArray retailersArray = jsonObject.optJSONArray( "category_ids" );
            if( retailersArray != null ) {
                retailer.category_ids= new int[retailersArray.length()];
                for( int i = 0; i < retailersArray.length(); i++ ) {
                    retailer.category_ids[i] = retailersArray.optInt( i );
                }
            }

        } catch( JSONException e ) {

        }

        return retailer;
    }

    public String getAny_brand_icon_url() {
        return any_brand_icon_url;
    }

    public void setAny_brand_icon_url(String any_brand_icon_url) {
        this.any_brand_icon_url = any_brand_icon_url;
    }

    public String getAny_brand_full_url() {
        return any_brand_full_url;
    }

    public void setAny_brand_full_url(String any_brand_full_url) {
        this.any_brand_full_url = any_brand_full_url;
    }

    public String getCard_signup_url() {
        return card_signup_url;
    }

    public void setCard_signup_url(String card_signup_url) {
        this.card_signup_url = card_signup_url;
    }

    public int[] getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(int[] category_ids) {
        this.category_ids = category_ids;
    }

    public String getExclusive_image_url() {
        return exclusive_image_url;
    }

    public void setExclusive_image_url(String exclusive_image_url) {
        this.exclusive_image_url = exclusive_image_url;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
