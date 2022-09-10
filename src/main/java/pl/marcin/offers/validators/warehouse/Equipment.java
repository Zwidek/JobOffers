package pl.marcin.offers.validators.warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Equipment {
    private String name;
    private String userId;
    @SerialNo(startsWith = "EQ", codeLength = 4)
    private String serialNumber;
}
