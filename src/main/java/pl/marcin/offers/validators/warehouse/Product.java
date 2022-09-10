package pl.marcin.offers.validators.warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private String description;
    @SerialNo(startsWith = "PL", codeLength = 5)
    private String serialNumber;
}
