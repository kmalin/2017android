package lt.birziska.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

import lt.birziska.grocerylist.Helpers.GroceriesListService;
import lt.birziska.grocerylist.Helpers.GroceryItemEditBaseActivity;
import lt.birziska.grocerylist.Helpers.GroceryListActivity;

public class GroceryItemEditActivity extends GroceryItemEditBaseActivity {

    private GroceriesListService service;
    private Integer groceryItemId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = new GroceriesListService(this);

        Intent intent = getIntent();
        String groceryId = intent.getStringExtra(GroceryListActivity.GROCERY_ID);
        if(groceryId != null && !groceryId.isEmpty()) {
            groceryItemId = Integer.parseInt(groceryId);
        } else {
            groceryItemId = null;
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveClick(view);
            }
        });
    }

    private void onSaveClick(View item) {

        // Create the object
        // Set properties: Name, Price, Quantity
        // Also set the Id, used to identify the grocery item in the list.
        // Id can be retrieved from the class field - groceryItemId

        // Call service method saveItem to save the object to the list
        // service.saveItem(groceryItem);

        // uncomment this to close edit screen
        // finish(); // goes back to the GroceryListActivity
    }
}