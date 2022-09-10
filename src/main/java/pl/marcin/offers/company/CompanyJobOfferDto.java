package pl.marcin.offers.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyJobOfferDto {
    private Long id;
    private String title;
    private Double minSalary;
    private Double maxSalary;
    private String location;
}
