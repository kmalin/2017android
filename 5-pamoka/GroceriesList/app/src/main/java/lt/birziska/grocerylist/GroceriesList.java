package lt.birziska.grocerylist;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GroceriesList {

    private Context context;
    private ArrayList<GroceryItemModel> groceriesList;

    public GroceriesList(Context context)
    {
        this.context = context;
    }

    public GroceryItemModel getGroceryItem(String groceryItemId) {
        if(groceriesList == null || groceriesList.isEmpty())
        {
            getGroceriesFromFile();
        }

        GroceryItemModel result = null;
        for(GroceryItemModel groceryItem : groceriesList){
            if(groceryItem.getId() == groceryItemId)
            {
                result = groceryItem;
                break;
            }
        }
        return result;
    }

    public ArrayList<GroceryItemModel> getGroceries(){

        if(!(groceriesList == null || groceriesList.isEmpty()))
        {
            return groceriesList;
        }

        getGroceriesFromFile();

        return groceriesList;
    }

    private void getGroceriesFromFile()
    {
        groceriesList = new ArrayList<>();
        try {
            // Load data
            String jsonString = loadJsonFromAsset("groceries.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray groceries = json.getJSONArray("groceries");

            for(int i = 0; i < groceries.length(); i++){
                JSONObject groceryJSON = groceries.getJSONObject(i);
                GroceryItemModel groceryItemModel = new GroceryItemModel();

                groceryItemModel.setId(groceryJSON.getString("id"));
                groceryItemModel.setName(groceryJSON.getString("name"));
                groceryItemModel.setPrice(new BigDecimal(groceryJSON.getString("price")));
                groceryItemModel.setQuantity(groceryJSON.getInt("quantity"));

                groceriesList.add(groceryItemModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
