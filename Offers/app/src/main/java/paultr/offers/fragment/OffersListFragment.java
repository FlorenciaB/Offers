package paultr.offers.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import paultr.offers.R;
import paultr.offers.activity.OfferDetailActivity;
import paultr.offers.adapter.OffersListAdapter;
import paultr.offers.model.Offer;
import paultr.offers.utility.DataUtil;

/**
 * Created by Paul on 12/22/15.
 */
public class OffersListFragment extends ListFragment {
    private OffersListAdapter mAdapter;

    public static OffersListFragment getInstance() {
        return new OffersListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new OffersListAdapter( getActivity(), 0 );

        for( Offer offer : DataUtil.getInstance().getOffers().values() ) {
            if( !offer.isFinished() ) {
                mAdapter.add( offer );
            }
        }

        setListAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if( Build.VERSION.SDK_INT < 21 ) {
            getListView().setDrawSelectorOnTop(true);
            getListView().setSelector(R.drawable.navigation_list_selector);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent( getActivity(), OfferDetailActivity.class );
        intent.putExtra( OfferDetailActivity.OFFER_ID_KEY, mAdapter.getItem( position ).getId() );
        startActivity( intent );
    }
}
