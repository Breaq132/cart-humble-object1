package starter.cart.api;
import java.math.BigDecimal; import java.util.List;
public record CartResponse(String cartId,List<CartItemResponse> items,BigDecimal totalPrice) {}