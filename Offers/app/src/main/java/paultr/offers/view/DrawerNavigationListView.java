package paultr.offers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import paultr.offers.R;
import paultr.offers.adapter.DrawerNavigationListAdapter;
import paultr.offers.event.NavigationDrawerEvent;
import paultr.offers.utility.EventBus;

public class DrawerNavigationListView extends ListView implements AdapterView.OnItemClickListener {
    public DrawerNavigationListView(Context context) {
        this(context, null);
    }

    public DrawerNavigationListView(Context context, AttributeSet attrs ) {
        this( context, attrs, 0 );
    }

    public DrawerNavigationListView( Context context, AttributeSet attrs, int style ) {
        super(context, attrs, style);

        DrawerNavigationListAdapter adapter = new DrawerNavigationListAdapter( getContext(), 0 );

        adapter.add( "Offers");
        adapter.add( "Retailers" );
        adapter.add( "Store Locations Map" );
        adapter.notifyDataSetChanged();
        setOnItemClickListener(this);
        setAdapter(adapter);

        //Should use a list fragment instead for the ripple effect

        setDrawSelectorOnTop(true);
        setSelector( R.drawable.navigation_list_selector );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getInstance().post( new NavigationDrawerEvent( (String) parent.getItemAtPosition( position ) ) );
    }
}
