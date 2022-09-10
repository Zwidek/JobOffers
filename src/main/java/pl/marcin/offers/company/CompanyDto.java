package pl.marcin.offers.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private String city;
    private Integer employees;
    private String telephone;
    private String email;
}
