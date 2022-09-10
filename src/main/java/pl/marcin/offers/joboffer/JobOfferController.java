package pl.marcin.offers.joboffer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/offers", produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_ATOM_XML_VALUE})
public class JobOfferController {
    private final JobOfferRepository jobOfferRepository;
    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferRepository jobOfferRepository, JobOfferService jobOfferService) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    List<JobOffer> getAllOffers() {
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferRepository.findAll().forEach(jobOfferList::add);
        return jobOfferList;
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<JobOfferDto> getOfferById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<JobOfferDto> saveJobOffer(@RequestBody JobOfferDto jobOfferDto) {
        JobOfferDto saveJobOffer = jobOfferService.saveOffer(jobOfferDto);
        URI savedJobOfferUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveJobOffer.getId())
                .toUri();
        return ResponseEntity.created(savedJobOfferUri).body(jobOfferDto);
    }

    @PatchMapping(value = "/{id}")
    ResponseEntity<?> updateJobOffer(@PathVariable Long id, @RequestBody JobOfferDto jobOfferDto) {
        return jobOfferService.updateOffer(id, jobOfferDto)
                .map(offer -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJobOffer(@PathVariable Long id) {
        jobOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
