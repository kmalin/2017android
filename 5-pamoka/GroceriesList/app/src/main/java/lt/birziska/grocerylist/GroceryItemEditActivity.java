package lt.birziska.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

import lt.birziska.grocerylist.Helpers.GroceryListActivity;
import lt.birziska.grocerylist.Helpers.GroceriesListService;

public class GroceryItemEditActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private GroceriesListService service;
    private Integer groceryItemId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);

        setToolbar();

        service = new GroceriesListService(this);

        Intent intent = getIntent();
        String groceryId = intent.getStringExtra(GroceryListActivity.GROCERY_ID);

        if(groceryId != null && !groceryId.isEmpty()) {
            groceryItemId = Integer.parseInt(groceryId);
        } else {
            groceryItemId = null;
        }

        displayGroceryItem(groceryItemId);
        setTitle(groceryItemId);



        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new SaveButtonClickListener());
    }

    private void displayGroceryItem(Integer id) {
        if(id != null)
        {
            GroceryItemInterface item = service.getItem(id);

            EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
            nameEditText.setText(item.getName());

            EditText priceEditText = (EditText)findViewById(R.id.priceEditText);
            priceEditText.setText(item.getPrice().toString());

            EditText quantityEditText = (EditText)findViewById(R.id.quantityEditText);
            quantityEditText.setText(item.getQuantity().toString());
        }
    }

    private void setTitle(Integer id)
    {
        if(id == null)
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
            GroceryItemModel groceryItem = getGroceryItem();
            service.saveItem(groceryItem);

            finish(); // goes back to the GroceryListActivity
        }

        private GroceryItemModel getGroceryItem()
        {
            GroceryItemModel groceryItem =  new GroceryItemModel();
            groceryItem.setId(groceryItemId);

            EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
            groceryItem.setName(nameEditText.getText().toString());

            EditText priceEditText = (EditText)findViewById(R.id.priceEditText);
            groceryItem.setPrice(new BigDecimal(priceEditText.getText().toString()));

            EditText quantityEditText = (EditText)findViewById(R.id.quantityEditText);
            groceryItem.setQuantity(new BigDecimal(quantityEditText.getText().toString()));

            return groceryItem;
        }
    }
}
