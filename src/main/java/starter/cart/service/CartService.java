package starter.cart.service;
import starter.cart.domain.*; import starter.cart.port.*;
import java.util.Objects;
public class CartService {
 private final CartRepository repository; private final ProductPriceVerifier verifier;
 public CartService(CartRepository repository,ProductPriceVerifier verifier){this.repository=Objects.requireNonNull(repository);this.verifier=Objects.requireNonNull(verifier);}
 public Cart addToCart(String cartId,String productId,int quantity){
  if(cartId==null || cartId.isBlank() || productId==null || productId.isBlank())throw new IllegalArgumentException("Cart ID and product ID required");
  if(quantity<=0)throw new IllegalArgumentException("Quantity must be positive");
  ProductInfo product=verifier.getVerifiedProduct(productId,quantity);
  if(product==null || !product.available())throw new IllegalArgumentException("Product not available: "+productId);
  Cart cart=repository.findById(cartId).orElseGet(()->new Cart(cartId));
  cart.addItem(new CartItem(product.productId(),product.productName(),product.verifiedPrice(),quantity));
  return repository.save(cart);
 }
 public Cart removeFromCart(String cartId,String productId){Cart cart=getCart(cartId);cart.removeItem(productId);return repository.save(cart);}
 public Cart getCart(String cartId) {
    return repository.findById(cartId)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Cart not found: " + cartId
                )
            );
}
}