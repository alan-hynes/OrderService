package ie.atu.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final ProductServiceFeignClient productServiceFeignClient;
    private final List<Order> orders = new ArrayList<>();

    @Autowired
    public OrderService(ProductServiceFeignClient productServiceFeignClient) {
        this.productServiceFeignClient = productServiceFeignClient;
    }

    public Order placeOrder(Order order) {
        Product product;

        try {
            product = productServiceFeignClient.getProductByID(order.getProductID());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Product with ID " + order.getProductID() + " not found");
        }

        if (product.getStock() <= 0) {
            throw new IllegalArgumentException("Product out of stock");
        }

        order.setTotalPrice(product.getPrice());

        if (order.getId() == null) {
            order.setId(generateOrderId());
        }

        if (order.getEmail() == null || order.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email must be provided for placing an order");
        }

        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    private Long generateOrderId() {
        return (long) (orders.size() + 1);
    }
}
