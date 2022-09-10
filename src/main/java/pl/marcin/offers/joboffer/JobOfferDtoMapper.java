package pl.marcin.offers.joboffer;

import org.springframework.stereotype.Service;
import pl.marcin.offers.company.CompanyRepository;

@Service
public class JobOfferDtoMapper {
    private final CompanyRepository companyRepository;

    public JobOfferDtoMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    protected JobOfferDto map(JobOffer jobOffer) {
        JobOfferDto jobOfferDto = new JobOfferDto();
        jobOfferDto.setId(jobOffer.getId());
        jobOfferDto.setTitle(jobOffer.getTitle());
        jobOfferDto.setDescription(jobOffer.getDescription());
        jobOfferDto.setRequirements(jobOffer.getRequirements());
        jobOfferDto.setDuties(jobOffer.getDuties());
        jobOfferDto.setLocation(jobOffer.getLocation());
        jobOfferDto.setMinSalary(jobOffer.getMinSalary());
        jobOfferDto.setMaxSalary(jobOffer.getMaxSalary());
        jobOfferDto.setDateAdded(jobOffer.getDateAdded());
        jobOfferDto.setCompanyId(jobOffer.getCompany().getId());
        jobOfferDto.setCompanyName(jobOffer.getCompany().getName());
        return jobOfferDto;
    }

    protected JobOffer map(JobOfferDto jobOfferDto) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(jobOfferDto.getId());
        jobOffer.setTitle(jobOfferDto.getTitle());
        jobOffer.setDescription(jobOfferDto.getDescription());
        jobOffer.setRequirements(jobOfferDto.getRequirements());
        jobOffer.setDuties(jobOfferDto.getDuties());
        jobOffer.setLocation(jobOfferDto.getLocation());
        jobOffer.setMinSalary(jobOfferDto.getMinSalary());
        jobOffer.setMaxSalary(jobOfferDto.getMaxSalary());
        companyRepository.findById(jobOfferDto.getCompanyId())
                .ifPresent(jobOffer::setCompany);
        return jobOffer;
    }
}
