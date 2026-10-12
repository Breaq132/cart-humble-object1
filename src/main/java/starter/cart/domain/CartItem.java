package starter.cart.domain;
import java.math.BigDecimal;
public class CartItem {
 private final String productId, productName; private final BigDecimal price; private int quantity;
 public CartItem(String productId, String productName, BigDecimal price, int quantity) {
  if (productId == null || productId.isBlank() || productName == null || productName.isBlank() || price == null || price.signum() < 0 || quantity <= 0) throw new IllegalArgumentException("Invalid cart item");
  this.productId=productId; this.productName=productName; this.price=price; this.quantity=quantity;
 }
 public String getProductId(){return productId;} public String getProductName(){return productName;}
 public BigDecimal getPrice(){return price;} public int getQuantity(){return quantity;}
 public BigDecimal getSubTotal(){return price.multiply(BigDecimal.valueOf(quantity));}
 public void incrementQuantity(int amount){if(amount<=0)throw new IllegalArgumentException("Quantity must be positive"); quantity=Math.addExact(quantity,amount);}
}