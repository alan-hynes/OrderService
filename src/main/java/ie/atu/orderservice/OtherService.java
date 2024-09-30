package ie.atu.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (name = "external-service", url = "http://localhost:8080")
public interface OtherService {
    @GetMapping("/getDetails")
    public String externalDetails();
}
