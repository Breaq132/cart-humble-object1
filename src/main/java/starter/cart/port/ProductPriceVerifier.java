package starter.cart.port;
import starter.cart.domain.ProductInfo;
public interface ProductPriceVerifier {ProductInfo getVerifiedProduct(String productId, int quantity);}