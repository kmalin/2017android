package lt.birziska.grocerylist.Helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

import lt.birziska.grocerylist.GroceryItemInterface;
import lt.birziska.grocerylist.R;

public class GroceryItemEditBaseActivity extends AppCompatActivity {

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

        EditText priceEditText = findViewById(R.id.priceEditText);
        priceEditText.addTextChangedListener(new TextChangedListener<EditText>(priceEditText) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                updateSum();
            }
        });

        EditText quantityEditText = findViewById(R.id.quantityEditText);
        quantityEditText.addTextChangedListener(new TextChangedListener<EditText>(quantityEditText) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                updateSum();
            }
        });
    }

    private void updateSum() {
        try {
            EditText priceEditText = findViewById(R.id.priceEditText);
            BigDecimal price = new BigDecimal(priceEditText.getText().toString());

            EditText quantityEditText = findViewById(R.id.quantityEditText);
            BigDecimal quantity = new BigDecimal(quantityEditText.getText().toString());


            BigDecimal sum = price.multiply(quantity);
            TextView nameEditText = findViewById(R.id.sumOutputView);
            nameEditText.setText(sum.toString());
        }
        catch (Exception exc) {
            TextView nameEditText = findViewById(R.id.sumOutputView);
            nameEditText.setText("");
        }
    }

    private void setToolbar()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

    private void displayGroceryItem(Integer id) {
        if(id != null)
        {
            GroceryItemInterface item = service.getItem(id);

            EditText nameEditText = findViewById(R.id.nameEditText);
            nameEditText.setText(item.getName());

            EditText priceEditText = findViewById(R.id.priceEditText);
            priceEditText.setText(item.getPrice().toString());

            EditText quantityEditText = findViewById(R.id.quantityEditText);
            quantityEditText.setText(item.getQuantity().toString());

            updateSum();
        }
    }
}