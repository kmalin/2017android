package lt.birziska.grocerylist.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import lt.birziska.grocerylist.GroceriesList;
import lt.birziska.grocerylist.GroceryItemModel;
import lt.birziska.grocerylist.GroceryAdapter;
import lt.birziska.grocerylist.R;

public class GroceryListActivity extends AppCompatActivity {

    //this is for edit grocery
    public static final String GROCERY_ID = "GroceryId";

    private ListView groceryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FabClickListener() );

        groceryListView = (ListView) findViewById(R.id.grocery_list_view);

        GroceriesList groceriesList = new GroceriesList(this);
        ArrayList<GroceryItemModel> groceryList = groceriesList.getGroceries();

        GroceryAdapter adapter = new GroceryAdapter(this, groceryList);
        groceryListView.setAdapter(adapter);
    }

    private class FabClickListener
            implements View.OnClickListener {
        @Override
        public void onClick(View item) {
            Intent intent = new Intent(GroceryListActivity.this, EditGroceryActivity.class);
            startActivity(intent);
        }
    }
}
