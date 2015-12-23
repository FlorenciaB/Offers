package paultr.offers.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import paultr.offers.R;
import paultr.offers.event.NavigationDrawerEvent;
import paultr.offers.fragment.OffersListFragment;
import paultr.offers.fragment.RetailerMapFragment;
import paultr.offers.fragment.RetailersListFragment;
import paultr.offers.utility.DataUtil;
import paultr.offers.utility.EventBus;

public class MainActivity extends AppCompatActivity {

    private String mCurrentFragmentTitle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //Just loading data synchronously for the demo
        DataUtil.getInstance().init( this );

        displayInitialFragment();
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, OffersListFragment.getInstance()).commit();
        mCurrentFragmentTitle = "Offers";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register( this );
    }

    @Override
    protected void onStop() {
        EventBus.getInstance().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onNavigationDrawerEvent( NavigationDrawerEvent event ) {
        mDrawerLayout.closeDrawers();

        if( event == null || TextUtils.isEmpty( event.section ) || event.section.equalsIgnoreCase( mCurrentFragmentTitle ) ) {
            return;
        }

        if( event.section.equalsIgnoreCase( "Offers" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, OffersListFragment.getInstance() ).commit();
        } else if( event.section.equalsIgnoreCase( "Retailers" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, RetailersListFragment.getInstance() ).commit();
        } else if( event.section.equalsIgnoreCase( "Store Locations Map" ) ) {
            getSupportFragmentManager().beginTransaction().replace( R.id.container, RetailerMapFragment.getInstance() ).commit();
        }

        mCurrentFragmentTitle = event.section;
    }

}
