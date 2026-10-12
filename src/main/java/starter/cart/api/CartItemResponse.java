package starter.cart.api;
import java.math.BigDecimal;
public record CartItemResponse(String productId,String productName,BigDecimal price,int quantity,BigDecimal subTotal) {}