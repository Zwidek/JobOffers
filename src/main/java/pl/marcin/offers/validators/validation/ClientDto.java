package pl.marcin.offers.validators.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClientDto {
    @NotNull
    @Size(min = 2, max = 100)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastName;
    @NotNull
    @Email
    @Size(min = 5, max = 100)
    private String email;
    @Min(value = 1)
    private int age;


}
