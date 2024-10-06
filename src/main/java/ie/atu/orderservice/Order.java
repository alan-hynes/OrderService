package ie.atu.orderservice;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Digits(integer = 5, fraction = 0, message = "Order ID must be 5 digits")
    private Long id;

    @Email(message = "Invalid email")
    private String email;

    @Min(value = 0, message = "Total price cannot be negative")
    private double totalPrice;

    @Positive(message = "Product ID must be greater than 0")
    private Long productID;

}
