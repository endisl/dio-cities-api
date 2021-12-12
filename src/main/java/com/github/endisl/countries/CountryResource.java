package com.github.endisl.countries;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryResource {

  private final CountryRepository repository;

  public CountryResource(final CountryRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Page<Country> countries(Pageable page) {

    return repository.findAll(page);
    //example endisl 111221: http://localhost:8080/countries?page=0&size=50&sort=id,asc
  }

  @GetMapping("/{id}")
  public ResponseEntity getOne(@PathVariable Long id) {
    Optional<Country> optional = repository.findById(id);

    if (optional.isPresent())
      return ResponseEntity.ok().body(optional.get());
    return ResponseEntity.notFound().build();

  }


}
