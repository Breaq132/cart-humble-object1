# Shopping Cart - Humble Object (Java 17 + Maven)

## Architecture
`CartController` is the Humble Object: it only forwards commands to `CartService` and maps domain objects to DTO responses. `CartService` implements use cases; `Cart` and `CartItem` implement domain behavior. Repository and price verification are interfaces, with in-memory implementations for this classroom demonstration.

## Requirements
Java JDK 17+ and Maven 3.9+.

## Run
```bash
mvn clean test
mvn exec:java
```

## Example products
P001: Ao thun, 150000 VND, available. P002: Quan jean, 350000 VND, available. P003: Ao khoac, 450000 VND, unavailable.

## Notes
This is a console demo, **not an HTTP REST API**. `CartController` deliberately does not use Spring or `ResponseEntity`. The repository is memory-only and resets on restart. The product verifier uses fixed sample data. In a real system, the price-change policy and persistence would need further design.

## Swing graphical interface
Run `mvn clean test` and then `mvn exec:java` to open a desktop cart table.
Select a product and quantity to add, or select a row and click remove.
Data is in-memory and resets when the app is restarted.
