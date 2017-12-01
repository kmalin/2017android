package lt.birziska.grocerylist.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import lt.birziska.grocerylist.GroceriesListService;
import lt.birziska.grocerylist.GroceryItemInterface;
import lt.birziska.grocerylist.Helpers.GroceryAdapter;
import lt.birziska.grocerylist.R;

public class GroceryListActivity extends AppCompatActivity {

    //this is for edit grocery
    public static final String GROCERY_ID = "GroceryId";

    private ListView groceryListView;
    private GroceriesListService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new FabClickListener() );

        groceryListView = (ListView) findViewById(R.id.grocery_list_view);

        service = new GroceriesListService(this);

        List<GroceryItemInterface> groceryList = service.getList();

        GroceryAdapter adapter = new GroceryAdapter(this, groceryList);
        groceryListView.setAdapter(adapter);
        groceryListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg1,int position, long id)
            {
                GroceryItemInterface entry = (GroceryItemInterface) adapterView.getAdapter().getItem(position);

                Intent intent = new Intent(GroceryListActivity.this, EditGroceryActivity.class);
                intent.putExtra(GROCERY_ID, entry.getId().toString());
                startActivity(intent);
            }
        });
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
