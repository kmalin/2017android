package lt.birziska.grocerylist;

import java.math.BigDecimal;

public class GroceryItemModel {
    private String id;
    public String getId(){ return id; }
    public void setId(String id){ this.id = id; }

    private String name;
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    private BigDecimal price;
    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price = price; }

    private Integer quantity;
    public Integer getQuantity(){ return quantity; }
    public void setQuantity(Integer quantity){ this.quantity = quantity; }
}
