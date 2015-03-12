package hu.ait.android.maggie.shoppinglist.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.ait.android.maggie.shoppinglist.R;
import hu.ait.android.maggie.shoppinglist.data.Item;

/**
 * Created by Magisus on 3/11/2015.
 */
public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView name;
        ImageView icon;
    }

    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_row, null);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.nameText);
            holder.icon = (ImageView) v.findViewById(R.id.iconImage);
            v.setTag(holder);
        }
        Item item = items.get(position);
        if(item != null){
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.name.setText(item.getName());
            holder.icon.setImageResource(item.getType().getIconId());
        }
        return v;
    }
}
