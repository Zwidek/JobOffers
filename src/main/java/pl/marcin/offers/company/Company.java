package pl.marcin.offers.company;

import lombok.Getter;
import lombok.Setter;
import pl.marcin.offers.joboffer.JobOffer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String city;
    private Integer employees;
    private String telephone;
    private String email;
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<JobOffer> jobOffers = new ArrayList<>();

}