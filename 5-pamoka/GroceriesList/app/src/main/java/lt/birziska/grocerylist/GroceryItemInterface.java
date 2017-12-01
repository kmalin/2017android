package lt.birziska.grocerylist;

import java.math.BigDecimal;

public interface GroceryItemInterface {

    Integer getId();
    String getName();
    BigDecimal getPrice();
    BigDecimal getQuantity();
    BigDecimal getSum();
}
