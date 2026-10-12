package starter.cart.infrastructure;
import starter.cart.domain.Cart; import starter.cart.port.CartRepository; import java.util.*;
public class InMemoryCartRepository implements CartRepository {
 private final Map<String,Cart> carts=new HashMap<>();
 public Optional<Cart> findById(String cartId){return Optional.ofNullable(carts.get(cartId));}
 public Cart save(Cart cart){carts.put(cart.getId(),cart);return cart;}
}