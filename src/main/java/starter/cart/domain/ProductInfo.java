package starter.cart.domain;
import java.math.BigDecimal;
public record ProductInfo(String productId, String productName, BigDecimal verifiedPrice, boolean available) {}