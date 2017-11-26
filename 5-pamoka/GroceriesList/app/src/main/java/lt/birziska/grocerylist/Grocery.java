package lt.birziska.grocerylist;

import java.math.BigDecimal;

public class Grocery {
    private String name;
    String getName(){ return name; }
    void setName(String name){ this.name = name; }

    private BigDecimal price;
    BigDecimal getPrice(){ return price; }
    void setPrice(BigDecimal price){ this.price = price; }

    private Integer quantity;
    Integer getQuantity(){ return quantity; }
    void setQuantity(Integer quantity){ this.quantity = quantity; }
}
