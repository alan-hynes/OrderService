package ie.atu.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "product-service", url = "http://localhost:8080")
public interface ProductServiceFeignClient {
    @GetMapping("/products/{id}")
    Product getProductByID(@PathVariable Long id);
}
