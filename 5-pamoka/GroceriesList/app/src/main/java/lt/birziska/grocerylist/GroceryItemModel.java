package lt.birziska.grocerylist;

import java.math.BigDecimal;

public class GroceryItemModel implements GroceryItemInterface {
    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;

    public Integer getId(){ return id; }
    public void setId(Integer id){ this.id = id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price = price; }

    public BigDecimal getQuantity(){ return quantity; }
    public void setQuantity(BigDecimal quantity){ this.quantity = quantity; }

    public BigDecimal getSum() {
        return price.multiply(quantity);
    }
}
