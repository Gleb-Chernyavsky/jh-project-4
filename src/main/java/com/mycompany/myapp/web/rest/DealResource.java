package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Deal;
import com.mycompany.myapp.repository.DealRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Deal}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DealResource {

    private final Logger log = LoggerFactory.getLogger(DealResource.class);

    private static final String ENTITY_NAME = "deal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealRepository dealRepository;

    public DealResource(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    /**
     * {@code POST  /deals} : Create a new deal.
     *
     * @param deal the deal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deal, or with status {@code 400 (Bad Request)} if the deal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deals")
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) throws URISyntaxException {
        log.debug("REST request to save Deal : {}", deal);
        if (deal.getId() != null) {
            throw new BadRequestAlertException("A new deal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Deal result = dealRepository.save(deal);
        return ResponseEntity.created(new URI("/api/deals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /deals} : Updates an existing deal.
     *
     * @param deal the deal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deal,
     * or with status {@code 400 (Bad Request)} if the deal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deals")
    public ResponseEntity<Deal> updateDeal(@RequestBody Deal deal) throws URISyntaxException {
        log.debug("REST request to update Deal : {}", deal);
        if (deal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Deal result = dealRepository.save(deal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /deals} : get all the deals.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deals in body.
     */
    @GetMapping("/deals")
    public ResponseEntity<List<Deal>> getAllDeals(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Deals");
        Page<Deal> page;
        if (eagerload) {
            page = dealRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = dealRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deals/:id} : get the "id" deal.
     *
     * @param id the id of the deal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deals/{id}")
    public ResponseEntity<Deal> getDeal(@PathVariable Long id) {
        log.debug("REST request to get Deal : {}", id);
        Optional<Deal> deal = dealRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(deal);
    }

    /**
     * {@code DELETE  /deals/:id} : delete the "id" deal.
     *
     * @param id the id of the deal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deals/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        log.debug("REST request to delete Deal : {}", id);
        dealRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
