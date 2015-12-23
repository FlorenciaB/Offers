package paultr.offers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import paultr.offers.model.Retailer;

public class RetailersListAdapter extends ArrayAdapter<Retailer> {

    public RetailersListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if( convertView == null ) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate( android.R.layout.simple_list_item_1, parent, false );

            holder.name = (TextView) convertView.findViewById( android.R.id.text1 );

            convertView.setTag( holder );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
