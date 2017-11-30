package lt.birziska.grocerylist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigDecimal;
import lt.birziska.grocerylist.GroceryItemModel;
import lt.birziska.grocerylist.R;

public class EditGroceryActivity  extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);

        setToolbar();

        Intent intent = getIntent();
        String groceryId = intent.getStringExtra(GroceryListActivity.GROCERY_ID);
        setTitle(groceryId);

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new SaveButtonClickListener());
    }

    private void setTitle(String groceryId)
    {
        if(groceryId == null || groceryId.isEmpty())
        {
            getSupportActionBar().setTitle("Create grocery");
        } else
        {
            getSupportActionBar().setTitle("Edit grocery");
        }
    }

    private void setToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private class SaveButtonClickListener
            implements View.OnClickListener {
        @Override
        public void onClick(View item) {
            //should save grocery
            Intent intent = new Intent(EditGroceryActivity.this, GroceryListActivity.class);
            startActivity(intent);
        }

        private GroceryItemModel getGroceryItem()
        {
            GroceryItemModel groceryItem =  new GroceryItemModel();

            EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
            groceryItem.setName(nameEditText.getText().toString());

            EditText priceEditText = (EditText)findViewById(R.id.priceEditText);
            groceryItem.setPrice(new BigDecimal(priceEditText.getText().toString()));

            EditText quantityEditText = (EditText)findViewById(R.id.quantityEditText);
            groceryItem.setQuantity(Integer.parseInt(quantityEditText.getText().toString()));

            return groceryItem;
        }
    }
}
