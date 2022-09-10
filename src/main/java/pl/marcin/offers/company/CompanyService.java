package pl.marcin.offers.company;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyJobOfferDtoMapper companyJobOfferDtoMapper;
    private final CompanyDtoMapper companyDtoMapper;

    protected Optional<CompanyDto> getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyDtoMapper::map);
    }

    protected List<CompanyJobOfferDto> getJobOffersByCompanyId(Long id) {
        return companyRepository.findById(id)
                .map(Company::getJobOffers)
                .orElse(Collections.emptyList())
                .stream().map(companyJobOfferDtoMapper::map)
                .toList();
    }

    protected CompanyDto saveCompany(CompanyDto companyDto) {
        Company company = companyDtoMapper.map(companyDto);
        Company saveCompany = companyRepository.save(company);
        return companyDtoMapper.map(saveCompany);
    }

    protected Optional<CompanyDto> replaceCompany(Long id, CompanyDto companyDto) {
        if (!companyRepository.existsById(id)) {
            return Optional.empty();
        }
        companyDto.setId(id);
        Company companyToUpdate = companyDtoMapper.map(companyDto);
        Company saveCompany = companyRepository.save(companyToUpdate);
        return Optional.of(companyDtoMapper.map(saveCompany));
    }

    protected void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}
