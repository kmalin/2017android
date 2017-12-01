package lt.birziska.grocerylist.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lt.birziska.grocerylist.GroceryItemInterface;
import lt.birziska.grocerylist.R;

public class GroceryAdapter extends BaseAdapter {

    private Context adapterContext;
    private LayoutInflater layoutInflater;
    private List<GroceryItemInterface> groceries;

    public GroceryAdapter(Context context, List<GroceryItemInterface> groceries) {
        adapterContext = context;
        this.groceries = groceries;
        layoutInflater = (LayoutInflater) adapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return groceries.size();
    }

    @Override
    public Object getItem(int i) {
        return groceries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = layoutInflater.inflate(R.layout.grocery_list_item, viewGroup, false);

        TextView groceryName = (TextView) rowView.findViewById(R.id.grocery_name);
        TextView groceryPrice = (TextView) rowView.findViewById(R.id.grocery_price);

        GroceryItemInterface item = (GroceryItemInterface) getItem(i);

        groceryName.setText(item.getName());
        groceryPrice.setText(item.getPrice().toString());

        return rowView;
    }
}