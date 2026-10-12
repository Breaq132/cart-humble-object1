package starter.cart.port;
import starter.cart.domain.Cart; import java.util.Optional;
public interface CartRepository {Optional<Cart> findById(String cartId); Cart save(Cart cart);}