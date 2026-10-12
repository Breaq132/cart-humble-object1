package starter.cart.infrastructure;
import starter.cart.domain.ProductInfo; import starter.cart.port.ProductPriceVerifier;
import java.math.BigDecimal; import java.util.Map;
public class StaticProductPriceVerifier implements ProductPriceVerifier {
 private final Map<String,ProductInfo> products=Map.of(
  "P001",new ProductInfo("P001","Ao thun",new BigDecimal("150000"),true),
  "P002",new ProductInfo("P002","Quan jean",new BigDecimal("350000"),true),
  "P003",new ProductInfo("P003","Ao khoac",new BigDecimal("450000"),false));
 public ProductInfo getVerifiedProduct(String productId,int quantity){return products.get(productId);}
}