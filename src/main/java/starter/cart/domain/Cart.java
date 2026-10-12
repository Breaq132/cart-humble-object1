package starter.cart.domain;
import java.math.BigDecimal; import java.util.*;
public class Cart {
 private final String id; private final List<CartItem> items = new ArrayList<>();
 public Cart(String id){if(id==null || id.isBlank())throw new IllegalArgumentException("Cart ID required");this.id=id;}
 public String getId(){return id;}
 public List<CartItem> getItems(){return Collections.unmodifiableList(items);}
 public void addItem(CartItem item){
  Objects.requireNonNull(item);
  for(CartItem existing:items){if(existing.getProductId().equals(item.getProductId())){
   if(existing.getPrice().compareTo(item.getPrice())!=0)throw new IllegalArgumentException("Price changed for existing item");
   existing.incrementQuantity(item.getQuantity());return;
  }} items.add(item);
 }
 public void removeItem(String productId){boolean removed=items.removeIf(i->i.getProductId().equals(productId));if(!removed)throw new IllegalArgumentException("Product not in cart: "+productId);}
 public BigDecimal getTotalPrice(){return items.stream().map(CartItem::getSubTotal).reduce(BigDecimal.ZERO,BigDecimal::add);}
}