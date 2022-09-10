package pl.marcin.offers.joboffer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobOfferDto {
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String duties;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private LocalDateTime dateAdded;
    private Long companyId;
    private String companyName;
}
