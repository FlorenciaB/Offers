package paultr.offers.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import paultr.offers.R;
import paultr.offers.model.Offer;
import paultr.offers.utility.DataUtil;

/**
 * Created by Paul on 12/22/15.
 */
public class OfferDetailActivity extends Activity {

    public static final String OFFER_ID_KEY = "offer_id_key";

    private Offer mOffer;

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private ImageView mLargeImage;
    private TextView mRetailersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_offer_detail );
        if( getIntent().getExtras() != null && getIntent().getExtras().containsKey(OFFER_ID_KEY) ) {
            mOffer = DataUtil.getInstance().getOffers().get( getIntent().getExtras().getInt( OFFER_ID_KEY ) );
        }

        if( mOffer == null ) {
            //Something went wrong. Handle better.
            finish();
        }

        initViews();

        mNameTextView.setText( mOffer.getName() );
        mDescriptionTextView.setText(mOffer.getDescription());
        Picasso.with( this ).load( mOffer.getLarge_url() ).into(mLargeImage);
        StringBuilder builder = new StringBuilder();
        if( mOffer.getRetailers().length > 0 ) {
            builder.append( "Offer available at the following retailers: " );
        }
        for( int i = 0; i < mOffer.getRetailers().length; i++ ) {
            if( DataUtil.getInstance().getRetailers().get( mOffer.getRetailers()[i] ) != null ) {
                builder.append("\n");
                builder.append(DataUtil.getInstance().getRetailers().get(mOffer.getRetailers()[i]).getName());
            }
        }

        mRetailersTextView.setText( builder.toString() );

    }

    private void initViews() {
        mNameTextView = (TextView) findViewById( R.id.name );
        mDescriptionTextView = (TextView) findViewById( R.id.description );
        mLargeImage = (ImageView) findViewById( R.id.large_image );
        mRetailersTextView = (TextView) findViewById( R.id.retailers );
    }
}
