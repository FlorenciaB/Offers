package paultr.offers.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.Map;

import paultr.offers.activity.OfferDetailActivity;
import paultr.offers.activity.RetailerDetailActivity;
import paultr.offers.adapter.RetailersListAdapter;
import paultr.offers.model.Retailer;
import paultr.offers.utility.DataUtil;

/**
 * Created by Paul on 12/22/15.
 */
public class RetailersListFragment extends ListFragment {

    private RetailersListAdapter mAdapter;

    public static RetailersListFragment getInstance() {
        return new RetailersListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new RetailersListAdapter( getActivity(), 0 );

        for( Retailer retailer : DataUtil.getInstance().getRetailers().values() ) {
            mAdapter.add( retailer );
        }

        setListAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent( getActivity(), RetailerDetailActivity.class );
        intent.putExtra( RetailerDetailActivity.RETAILER_ID_KEY, mAdapter.getItem( position ).getId() );
        startActivity(intent);
    }

}
