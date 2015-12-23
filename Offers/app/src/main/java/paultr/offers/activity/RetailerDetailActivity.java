package paultr.offers.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import paultr.offers.R;
import paultr.offers.model.Offer;
import paultr.offers.model.Retailer;
import paultr.offers.utility.DataUtil;

/**
 * Created by Paul on 12/22/15.
 */
public class RetailerDetailActivity extends Activity {

    public static final String RETAILER_ID_KEY = "retailer_id_key";

    private Retailer mRetailer;

    private TextView mNameTextView;
    private ImageView mLargeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        if( getIntent().getExtras() != null && getIntent().getExtras().containsKey(RETAILER_ID_KEY) ) {
            mRetailer = DataUtil.getInstance().getRetailers().get( getIntent().getExtras().getInt( RETAILER_ID_KEY ) );
        }

        if( mRetailer == null ) {
            //Something went wrong. Handle better.
            finish();
        }

        initViews();

        mNameTextView.setText(mRetailer.getName());
        if(!TextUtils.isEmpty( mRetailer.getExclusive_image_url() ) ) {
            Picasso.with(this).load(mRetailer.getExclusive_image_url()).into(mLargeImage);
        }

    }

    private void initViews() {
        mNameTextView = (TextView) findViewById( R.id.name );
        mLargeImage = (ImageView) findViewById( R.id.large_image );
    }
}
