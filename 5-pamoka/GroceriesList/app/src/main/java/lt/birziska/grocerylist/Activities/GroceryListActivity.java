package lt.birziska.grocerylist.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;

import lt.birziska.grocerylist.GroceriesList;
import lt.birziska.grocerylist.Grocery;
import lt.birziska.grocerylist.GroceryAdapter;
import lt.birziska.grocerylist.R;

public class GroceryListActivity extends AppCompatActivity {

    private ListView groceryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FabClickListener() );

        groceryListView = (ListView) findViewById(R.id.grocery_list_view);

        ArrayList<Grocery> groceryList = GroceriesList.getGroceriesFromFile(this);

        GroceryAdapter adapter = new GroceryAdapter(this, groceryList);
        groceryListView.setAdapter(adapter);
    }

    private class FabClickListener
            implements View.OnClickListener {

        @Override
        public void onClick(View item) {
            // go to grocery creation
        }
    }
}
