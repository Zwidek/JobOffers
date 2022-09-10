package pl.marcin.offers.company;

import org.springframework.stereotype.Service;
import pl.marcin.offers.joboffer.JobOffer;

@Service
public class CompanyJobOfferDtoMapper {
    public CompanyJobOfferDto map(JobOffer jobOffer) {
        CompanyJobOfferDto companyJobOfferDtoMap = new CompanyJobOfferDto();
        companyJobOfferDtoMap.setId(jobOffer.getId());
        companyJobOfferDtoMap.setTitle(jobOffer.getTitle());
        companyJobOfferDtoMap.setLocation(jobOffer.getLocation());
        companyJobOfferDtoMap.setMinSalary(jobOffer.getMinSalary());
        companyJobOfferDtoMap.setMaxSalary(jobOffer.getMaxSalary());
        return companyJobOfferDtoMap;
    }
}
