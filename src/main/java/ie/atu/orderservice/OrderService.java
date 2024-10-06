package ie.atu.orderservice;

import ie.atu.productservice.Product

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final ProductServiceFeignClient productService;
    private List<Order> orders = new ArrayList<>();

    @Autowired
    public OrderService(ProductServiceFeignClient productService) {
        this.productService = productService;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order placeOrder(Order order) {
        Product product = productService.getProductById(order.getProductID());
        if (product.getStock() <= 0) {
            throw new IllegalArgumentException("Product out of stock");
        }

        orders.add(order);
        return order;
    }

}
