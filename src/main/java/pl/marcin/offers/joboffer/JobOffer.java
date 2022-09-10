package pl.marcin.offers.joboffer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.marcin.offers.company.Company;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String requirements;
    private String duties;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private LocalDateTime dateAdded;
    private Integer submissions;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @JsonProperty
    private String companyName() {
        return company.getName();
    }

}