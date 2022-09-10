package pl.marcin.offers.company;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping()
    List<Company> getAllCompanies() {
        List<Company> getAllCompanies = new ArrayList<>();
        companyRepository.findAll().forEach(getAllCompanies::add);
        return getAllCompanies;
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/offers")
    ResponseEntity<List<CompanyJobOfferDto>> getCompanyOffers(@PathVariable Long id) {
        if (companyService.getCompanyById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyService.getJobOffersByCompanyId(id));
    }

    @PostMapping
    ResponseEntity<CompanyDto> saveCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto saveCompany = companyService.saveCompany(companyDto);
        URI saveCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveCompany.getId())
                .toUri();
        return ResponseEntity.created(saveCompanyUri).body(saveCompany);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        return companyService.replaceCompany(id,companyDto)
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
