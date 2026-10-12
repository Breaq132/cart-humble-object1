
package starter;

import javax.swing.SwingUtilities;

import starter.cart.api.CartController;
import starter.cart.api.CartFrame;
import starter.cart.domain.Cart;
import starter.cart.infrastructure.InMemoryCartRepository;
import starter.cart.infrastructure.StaticProductPriceVerifier;
import starter.cart.service.CartService;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // 1. Khởi tạo Repository
            InMemoryCartRepository repository =
                    new InMemoryCartRepository();

            // 2. Tạo giỏ hàng ban đầu C001
            Cart cart = new Cart("C001");
            repository.save(cart);

            // 3. Khởi tạo Service
            CartService service = new CartService(
                    repository,
                    new StaticProductPriceVerifier()
            );

            // 4. Khởi tạo Controller
            CartController controller =
                    new CartController(service);

            // 5. Hiển thị giao diện Swing
            CartFrame frame = new CartFrame(controller);
            frame.setVisible(true);
        });
    }
}
