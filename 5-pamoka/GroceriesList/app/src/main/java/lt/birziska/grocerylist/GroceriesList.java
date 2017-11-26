package lt.birziska.grocerylist;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

public class GroceriesList {

    public static ArrayList<Grocery> getGroceriesFromFile(Context context){
        final ArrayList<Grocery> groceriesList = new ArrayList<>();

        try {
            // Load data
            String jsonString = loadJsonFromAsset("groceries.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray groceries = json.getJSONArray("groceries");

            for(int i = 0; i < groceries.length(); i++){
                JSONObject groceryJSON = groceries.getJSONObject(i);
                Grocery grocery = new Grocery();


                grocery.setName(groceryJSON.getString("name"));
                grocery.setPrice(new BigDecimal(groceryJSON.getString("price")));
                grocery.setQuantity(groceryJSON.getInt("quantity"));

                groceriesList.add(grocery);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return groceriesList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
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
