package lt.birziska.grocerylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GroceryListActivity extends AppCompatActivity {

    private ListView groceryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        groceryListView = (ListView) findViewById(R.id.grocery_list_view);

        ArrayList<Grocery> groceryList = GroceriesList.getGroceriesFromFile(this);

        GroceryAdapter adapter = new GroceryAdapter(this, groceryList);
        groceryListView.setAdapter(adapter);
    }
}
