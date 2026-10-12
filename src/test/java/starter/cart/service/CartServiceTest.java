package starter.cart.service;
import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
import starter.cart.domain.Cart; import starter.cart.infrastructure.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class CartServiceTest {
 private CartService service;
 @BeforeEach void setUp(){service=new CartService(new InMemoryCartRepository(),new StaticProductPriceVerifier());}
 @Test void addValidProduct(){Cart cart=service.addToCart("C1","P001",2);assertEquals(1,cart.getItems().size());assertEquals(new BigDecimal("300000"),cart.getTotalPrice());}
 @Test void addSameProductIncrementsQuantity(){service.addToCart("C1","P001",2);Cart cart=service.addToCart("C1","P001",3);assertEquals(5,cart.getItems().get(0).getQuantity());}
 @Test void rejectZeroAndNegativeQuantity(){assertThrows(IllegalArgumentException.class,()->service.addToCart("C1","P001",0));assertThrows(IllegalArgumentException.class,()->service.addToCart("C1","P001",-1));}
 @Test void rejectUnknownProduct(){assertThrows(IllegalArgumentException.class,()->service.addToCart("C1","BAD",1));}
 @Test void rejectUnavailableProduct(){assertThrows(IllegalArgumentException.class,()->service.addToCart("C1","P003",1));}
 @Test void removeProduct(){service.addToCart("C1","P001",1);Cart cart=service.removeFromCart("C1","P001");assertTrue(cart.getItems().isEmpty());}
 @Test void totalForMultipleItems(){service.addToCart("C1","P001",2);Cart cart=service.addToCart("C1","P002",1);assertEquals(new BigDecimal("650000"),cart.getTotalPrice());}
 @Test void missingCartThrows(){assertThrows(IllegalArgumentException.class,()->service.getCart("missing"));}
 @Test void removeMissingProductThrows(){service.addToCart("C1","P001",1);assertThrows(IllegalArgumentException.class,()->service.removeFromCart("C1","P002"));}
}