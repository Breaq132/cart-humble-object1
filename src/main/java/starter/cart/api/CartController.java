package starter.cart.api;
import starter.cart.domain.Cart; import starter.cart.service.CartService;
import java.util.Objects;
/** Humble Object: delegates business logic to CartService. No HTTP framework required. */
public class CartController {
 private final CartService service;
 public CartController(CartService service){this.service=Objects.requireNonNull(service);}
 public CartResponse handleAddToCart(CartRequest request){return toResponse(service.addToCart(request.cartId(),request.productId(),request.quantity()));}
 public CartResponse handleRemoveFromCart(RemoveCartItemRequest request){return toResponse(service.removeFromCart(request.cartId(),request.productId()));}
 public CartResponse handleGetCart(String cartId){return toResponse(service.getCart(cartId));}
 private CartResponse toResponse(Cart cart){return new CartResponse(cart.getId(),cart.getItems().stream().map(i->new CartItemResponse(i.getProductId(),i.getProductName(),i.getPrice(),i.getQuantity(),i.getSubTotal())).toList(),cart.getTotalPrice());}
}