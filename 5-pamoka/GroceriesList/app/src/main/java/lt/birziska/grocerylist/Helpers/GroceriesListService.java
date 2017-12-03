package lt.birziska.grocerylist.Helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lt.birziska.grocerylist.GroceryItemInterface;

public class GroceriesListService {

    private static final String filename = "storage.json";
    private Context context;

    public GroceriesListService(Context context) {
        this.context = context;
    }

    public List<GroceryItemInterface> getList() {
        List<GroceryItemTempModel> list = loadListFromFile();
        List<GroceryItemInterface> result = new ArrayList<>();
        for (GroceryItemInterface item: list) {
            result.add(item);
        }
        return result;
    }

    public GroceryItemInterface getItem(final int groceryId) {
        List<GroceryItemTempModel> list = loadListFromFile();
        for (GroceryItemInterface item: list) {
            if (item.getId() == groceryId){
                return item;
            }
        }
        return null;
    }

    public int saveItem(GroceryItemInterface item) {

        Integer groceryId = item.getId();
        List<GroceryItemTempModel> list = loadListFromFile();

        // add new
        if (groceryId == null) {
            GroceryItemTempModel tempModel = new GroceryItemTempModel();
            tempModel.setName(item.getName());
            tempModel.setPrice(item.getPrice());
            tempModel.setQuantity(item.getQuantity());
            tempModel.setId(getNextId(list));
            list.add(tempModel);
            groceryId = tempModel.getId();
        }
        // update existing
        else {

            for (GroceryItemTempModel existingItem: list) {
                if (existingItem.getId() == groceryId){

                    existingItem.setName(item.getName());
                    existingItem.setPrice(item.getPrice());
                    existingItem.setQuantity(item.getQuantity());
                    break;
                }
            }
        }

        saveListToFile(list);
        return groceryId;
    }

    private Integer getNextId(List<GroceryItemTempModel> list) {
        int currentId = 0;
        for (GroceryItemInterface item: list) {
            if (item.getId() != null && item.getId() > currentId) {
                currentId = item.getId();
            }
        }
        return currentId + 1;
    }

    private List<GroceryItemTempModel> loadListFromFile()
    {
        List<GroceryItemTempModel> result = new ArrayList<>();
        try {
            // Load data
            String jsonString = loadJsonFromFile();
            if (jsonString == null){
                return result;
            }
            JSONObject json = new JSONObject(jsonString);
            JSONArray groceries = json.getJSONArray("groceries");

            for(int i = 0; i < groceries.length(); i++){
                JSONObject groceryJSON = groceries.getJSONObject(i);
                GroceryItemTempModel groceryItemModel = new GroceryItemTempModel();

                if (!groceryJSON.has("id")
                        || !groceryJSON.has("name")
                        || !groceryJSON.has("price")
                        || !groceryJSON.has("quantity")) {
                    continue; // this is corrupted record - skip
                }

                groceryItemModel.setId(groceryJSON.getInt("id"));
                groceryItemModel.setName(groceryJSON.getString("name"));
                groceryItemModel.setPrice(new BigDecimal(groceryJSON.getString("price")));
                groceryItemModel.setQuantity(new BigDecimal(groceryJSON.getString("quantity")));

                result.add(groceryItemModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void saveListToFile(List<GroceryItemTempModel> list) {
        try {
            JSONObject json = new JSONObject();
            JSONArray groceries = new JSONArray();
            json.put("groceries", groceries);

            for(GroceryItemInterface item: list){
                JSONObject groceryJSON = new JSONObject();

                groceryJSON.put("id", item.getId());
                groceryJSON.put("name", item.getName());
                groceryJSON.put("price", item.getPrice());
                groceryJSON.put("quantity", item.getQuantity());

                groceries.put(groceryJSON);
            }

            // save data
            String jsonString = json.toString(4);
            saveJsonToFile(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJsonFromFile() {
        String json = null;

        try {

            File f = new File(context.getFilesDir(), filename);
            if(!f.exists() || f.isDirectory()) {
                return null;
            }

            InputStream is = context.openFileInput(filename);
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

    private void saveJsonToFile(String json) {
        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

                                                                                                                                                   private class GroceryItemTempModel implements GroceryItemInterface { private Integer id; private String name; private BigDecimal price; private BigDecimal quantity; public Integer getId(){ return id; } public void setId(Integer id){ this.id = id; } public String getName(){ return name; } public void setName(String name){ this.name = name; } public BigDecimal getPrice(){ return price; } public void setPrice(BigDecimal price){ this.price = price; } public BigDecimal getQuantity(){ return quantity; } public void setQuantity(BigDecimal quantity){ this.quantity = quantity; } public BigDecimal getSum() { return price.multiply(quantity); } }
}