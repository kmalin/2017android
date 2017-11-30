package lt.birziska.grocerylist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import lt.birziska.grocerylist.R;

public class EditGroceryActivity  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String groceryId = intent.getStringExtra(GroceryListActivity.GROCERY_ID);
        if(groceryId != null && !groceryId.isEmpty())
        {
            setTitle("Edit grocery");
        } else
        {
            setTitle("Create grocery");
        }
    }
}
