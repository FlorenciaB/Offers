package paultr.offers.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import paultr.offers.R;
import paultr.offers.model.StoreLocation;
import paultr.offers.utility.DataUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataUtil.getInstance().init( this );
    }
}
